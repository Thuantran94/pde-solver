package pde;

import java.io.IOException;

public final class MainFEM extends FEM{
    private final String FUNCTION_UEXACT,FUNCTION_DIV_UEXACT,FUNCTION_F;
    private double[][] GCOORD;
    private final float lampda1, lampda2;
    
    //<editor-fold defaultstate="collapsed" desc="Contructor">

    // contructor parabolic 1D
    public MainFEM(int nx, int nt, float dt, float xa, float xb, float kappa, float alpha, float beta, int Gx, String typeBoundary, 
                   String typeElement, String typeScheme, float lampda1, float lampda2, String uex, String duex, String funcf ,double[][]gcoord ) {
        super(nx, nt, dt, xa, xb, kappa, alpha, beta, Gx, typeBoundary, typeElement, typeScheme);
        this.lampda1 = lampda1;
        this.lampda2 = lampda2;
        this.FUNCTION_UEXACT = uex;
        this.FUNCTION_DIV_UEXACT = duex;
        this.FUNCTION_F = funcf;
        this.GCOORD = gcoord;
    }
    // contructor elliptic 1D
    public MainFEM(int nx,float xa, float xb, float alpha, float beta, int Gx, String typeBoundary, String typeElement,
                   float lampda1, float lampda2, String uex, String duex, String funcf ,double[][]gcoord) {
        super(nx, xa, xb, alpha, beta, Gx, typeBoundary, typeElement);
        this.lampda1 = lampda1;
        this.lampda2 = lampda2;
        this.FUNCTION_UEXACT = uex;
        this.FUNCTION_DIV_UEXACT = duex;
        this.FUNCTION_F = funcf;
        this.GCOORD = gcoord;
    }
    // contructor parabolic 2D
    public MainFEM(int nx,int ny,int nt,float dt,float xa,float xb,float ya, float yb,float kappa, float alpha,float beta,int Gx, int Gy,
                    String typeBoundary, String typeElement,String typeScheme, float lampda1, float lampda2,String uex, String duex, String funcf ,double[][]gcoord ){
        super(nx,ny,nt,dt,xa,xb,ya,yb,kappa,alpha,beta,Gx,Gy,typeBoundary,typeElement,typeScheme);
        this.lampda1 = lampda1;
        this.lampda2 = lampda2;
        this.FUNCTION_UEXACT = uex;
        this.FUNCTION_DIV_UEXACT = duex;
        this.FUNCTION_F = funcf;
        this.GCOORD = gcoord;
    }
    // contructor  elliptic 2D
    public MainFEM(int nx, int ny, float xa, float xb, float ya, float yb, float alpha, float beta, int Gx, int Gy, String typeBoundary,
            String typeElement, float lampda1, float lampda2,String uex, String duex, String funcf ,double[][]gcoord ) {
        super(nx, ny, xa, xb, ya, yb, alpha, beta, Gx, Gy, typeBoundary, typeElement);
        this.lampda1 = lampda1;
        this.lampda2 = lampda2;
        this.FUNCTION_UEXACT = uex;
        this.FUNCTION_DIV_UEXACT = duex;
        this.FUNCTION_F = funcf;
        this.GCOORD = gcoord;
    }

    //</editor-fold>
    
    public void mainEllipticFEM1D() throws IOException  {
        MainFEM.FEM1D elliptic = new MainFEM.FEM1D(getNx(), getXa(), getXb(), getAlpha(), getBeta(), getGx(), getTypeBoundary(), getTypeElement());
        

        //<editor-fold defaultstate="collapsed" desc="Init values">
        int nn = elliptic.nodeX();
        double[] gcoord = null;
        if ( GCOORD == null ){
            gcoord = elliptic.createMesh(getXa(), getXb(), nn);
        }else{
            for (int i = 0; i < GCOORD.length; i++) 
                gcoord[i] = GCOORD[i][0];
        }

        int num_nod = elliptic.numNode(getTypeElement());
        
        int[][] element = elliptic.matrixElement();
        //</editor-fold>

        double[][] A = null, K = null;
        double[] b = null, F = null;

        double[][] Gauss = elliptic.quadrature1D();

        for (int e = 0; e < getNx(); e++) {
            // The coordinate of eth element
            double[] coord = elliptic.coordElement(e, element, gcoord);

            // Computing the values of f function at coordinates elements.
            double[]f = new double[num_nod];
            for (int i = 0; i < num_nod; i++) {
                f[i] = elliptic.valueMath(FUNCTION_F,coord[i]);
            }

            for (int q = 0; q < Gauss.length; q++) {
                double point = Gauss[q][1];

                // Jacobi matrix
                double Jacobi = (coord[num_nod- 1] - coord[0]) / 2f;
                double invJ = 1 / Jacobi;

                // Langrange basis function.
                double[][] Lagrange = elliptic.lagrangeBasis(point);

                // computing N anh divN
                double[][] NdivN = elliptic.find_N_divN(invJ, Lagrange);
                // COMPUTING ELEMENT STIFFNESS AT QUADRATURE POINT
                A = elliptic.find_A(q, A, NdivN, Gauss, Jacobi);
                b = elliptic.find_b(q, b, NdivN, Gauss, f, Jacobi);
            }
            


            // Stiffness matrix K and the left hand side of the stiffness matrix F.
            K = elliptic.create_K(e, element, A, K);
            F = elliptic.create_F(e, element, b, F);
        }
        // APPLY ESSENTIAL BOUNDARY CONDITIONS
        double[] bval = elliptic.ValueBoundary(FUNCTION_UEXACT,FUNCTION_DIV_UEXACT,lampda1, lampda2);

        F = elliptic.applyBoundary(bval, K, F,lampda1,lampda2);

        double[] u_dis = elliptic.solveEquation(K, F);

        //test
        double[] u_ex = new double[nn];
        for (int i = 0; i < nn; i++) 
            u_ex[i] = elliptic.valueMath(FUNCTION_UEXACT, gcoord[i]);
        elliptic.writeFile(u_ex, "u_exact.txt");
        elliptic.writeFile(u_dis, "u_dis.txt");
        elliptic.writeFile(gcoord, "gcoord.txt");
        elliptic.writeFile(elliptic.maxError(u_dis, u_ex), "maxError.txt");
        System.out.println("Max Error = "+elliptic.maxError(u_dis, u_ex));
    }
    
    public void mainEllipticFEM2D() throws IOException {
        FEM.FEM2D elliptic = new FEM.FEM2D(getNx(), getNy(), getXa(), getXb(), getYa(), getYb(), getAlpha(), getBeta(),getGx(),getGy(), getTypeBoundary(), getTypeElement());


        //<editor-fold defaultstate="collapsed" desc="Init values">
        int nn = elliptic.numberNode();
        int num_nod = elliptic.numNode(getTypeElement());
        //double[] x = elliptic.createMesh(getXa(), getXb(), getNx());
        //double[] y = elliptic.createMesh(getYa(), getYb(), getNy());
        if (GCOORD == null )
            GCOORD = elliptic.createMesh2D();
        
        int[][] element = elliptic.matrixElement();
        //</editor-fold>
        double[][] A = null, K = null;
        double[] b = null, F = null;

        double[][] Gauss = elliptic.quadrature2D();    
        for (int e = 0; e < elliptic.numElement(); e++) {
            
            // The coordinate of eth element
            double[][] uv = elliptic.coordElement(e, element, GCOORD);

            // Computing the values of f function at coordinates elements.
            double[]f = new double[num_nod];
            for (int i = 0; i < num_nod; i++) {
                 f[i] = valueMath(FUNCTION_F,uv[i][0],uv[i][1]);
            }

            for (int q = 0; q < Gauss.length; q++) {
                double[] point = elliptic.getPointGauss(q);
                // Jacobi matrix
                double[][] Jacobi = elliptic.jacobiMatrix(uv, point);
                double detJ = elliptic.detJacobi(Jacobi);
                double[][] invJ = elliptic.invertJacobi(Jacobi, detJ);
                // Langrange basis function.
                double[][] Lagrange = elliptic.lagrangeBasis(point);
                // computing N anh divN
                double[][] NdivN = elliptic.find_N_divN(invJ, Lagrange);

                // COMPUTING ELEMENT STIFFNESS AT QUADRATURE POINT
                A = elliptic.find_A(q, A, NdivN, Gauss, detJ);
                b = elliptic.find_b(q, b, NdivN, Gauss, f, detJ);
            }

            // Stiffness matrix K and the left hand side of the stiffness matrix F.
            K = elliptic.create_K(e, element, A, K);
            F = elliptic.create_F(e, element, b, F);
        }
        // APPLY ESSENTIAL BOUNDARY CONDITIONS
        int[] nodebc = elliptic.nodeBoundary(GCOORD);
        double[] valbc = elliptic.valueBoundary(GCOORD,FUNCTION_UEXACT,FUNCTION_DIV_UEXACT);

        F = elliptic.applyBoundary(nodebc, valbc, K, F);
        K = elliptic.applyBoundary(nodebc, K);

        double[] u_dis = elliptic.solveEquation(K, F);
        
        
        
        //test
        double[] u_ex = new double[nn];
        for (int i = 0; i < nn; i++) 
            u_ex[i] = elliptic.valueMath(FUNCTION_UEXACT,GCOORD[i][0], GCOORD[i][1]);

        elliptic.writeFile(u_ex, "u_exact.txt");
        elliptic.writeFile(u_dis, "u_dis.txt");
        elliptic.writeFile(GCOORD,GCOORD.length,2, "gcoord.txt");
        elliptic.writeFile(elliptic.maxError(u_dis, u_ex), "maxError.txt");
        
        System.out.println("Max Error = "+elliptic.maxError(u_dis, u_ex));

    }
    
    public void mainParabolicFEM1D(){
    
    }
    
    public void mainParabolicFEM2D(){
    
    }
    
}
