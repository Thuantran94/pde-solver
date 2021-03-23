/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pde;




public class FEM extends PDE {
    private int Gx,Gy;
    
    //<editor-fold defaultstate="collapsed" desc="Contructor">
    

    public FEM(int nx,int nt,float dt,float xa,float xb,float kappa, float alpha,float beta,int Gx,String typeBoundary, String typeElement,String typeScheme){
        super(nx,nt,dt,xa,xb,kappa,alpha,beta,typeBoundary,typeElement,typeScheme);
        this.Gx = Gx;
    }
    public FEM(int nx,float xa,float xb, float alpha,float beta,int Gx,String typeBoundary, String typeElement){
        super(nx,xa,xb,alpha,beta,typeBoundary,typeElement);
        this.Gx = Gx;
}
    public FEM(int nx,int ny,int nt,float dt,float xa,float xb,float ya, float yb,float kappa, float alpha,float beta,int Gx, int Gy, String typeBoundary, String typeElement,String typeScheme){
        super(nx,ny,nt,dt,xa,xb,ya,yb,kappa,alpha,beta,typeBoundary,typeElement,typeScheme);
        this.Gx   = Gx;
        this.Gy   = Gy;
}
    public FEM(int nx,int ny,float xa,float xb,float ya, float yb,float alpha,float beta,int Gx, int Gy, String typeBoundary, String typeElement){
        super(nx,ny,xa,xb,ya,yb,alpha,beta,typeBoundary,typeElement);
        this.Gx   = Gx;
        this.Gy   = Gy;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GET - SET">
    

    public  int getGx() {
        return Gx;
    }

    public  int getGy() {
        return Gy;
    }

    public void setGx(int Gx) {
        this.Gx = Gx;
    }

    public void setGy(int Gy) {
        this.Gy = Gy;
    }


    
    //</editor-fold>
    
    
    class FEM1D extends FEM implements myFunc{
        private int num_nod;

        // contructor parabolic 1D
        public FEM1D(int nx,int nt,float dt,float xa,float xb,float kappa, float alpha,float beta,int Gx,String typeBoundary, String typeElement,String typeScheme){
            super(nx,nt,dt,xa,xb,kappa,alpha,beta,Gx,typeBoundary,typeElement,typeScheme);
        }
        // contructor elliptic 1D
        public FEM1D(int nx,float xa,float xb, float alpha,float beta,int Gx,String typeBoundary, String typeElement){
            super(nx,xa,xb,alpha,beta,Gx,typeBoundary,typeElement);
    }

        public void setNum_nod(int num_nod) {
        this.num_nod = num_nod;
        }
         
        public int getNum_nod() {
            return num_nod;
        }
        //<editor-fold defaultstate="collapsed" desc="Init somthing">   

    @Override
    public double[] initvector(int n) {
        double[] u = new double[n];
        for (int i = 0; i < n; i++) {
            u[i] = 0;
        }
        return u;
    }

    @Override
    public double[][] initmatrix(int m, int n) {
        double[][] A = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = 0;
            }
        }
        return A;
    }

    public int numNode( String typeElement) {

        if (typeElement.compareTo("L2") == 0)
            num_nod = 2;
        if (typeElement.compareTo("L3") == 0)
            num_nod = 3;
        if (typeElement.compareTo("L4") == 0) 
            num_nod = 4;
        setNum_nod(num_nod);
        return getNum_nod();
    }
    //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Gauss , matrix element and Lagrange">
        public double[][] quadrature1D() {
            double[][] Gauss = new double[1][1];
            if (getGx() > 5) 
                setGx(5);
            
            if (getGx() == 1) {
                Gauss = new double[1][2];
                //weight
                Gauss[0][0] = 2.000000000000000f;
                //point
                Gauss[0][1] = 0.000000000000000f;
            } else if (getGx() == 2) {
                Gauss = new double[2][2];
                Gauss[0][0] = 1.000000000000000f;
                Gauss[1][0] = 1.000000000000000f;

                Gauss[0][1] = 0.577350269189626f;
                Gauss[1][1] = -0.577350269189626f;
            } else if (getGx() == 3) {
                Gauss = new double[3][2];
                Gauss[0][0] = 0.555555555555556f;
                Gauss[1][0] = 0.555555555555556f;
                Gauss[2][0] = 0.888888888888889f;

                Gauss[0][1] = 0.774596669241483f;
                Gauss[1][1] = -0.774596669241483f;
                Gauss[2][1] = 0.000000000000000f;
            } else if (getGx() == 4) {
                Gauss = new double[4][2];
                Gauss[0][0] = 0.347854845137454f;
                Gauss[1][0] = 0.347854845137454f;
                Gauss[2][0] = 0.652145154862546f;
                Gauss[3][0] = 0.652145154862546f;

                Gauss[0][1] = 0.861134311594053f;
                Gauss[1][1] = -0.861134311594053f;
                Gauss[2][1] = 0.339981043584856f;
                Gauss[3][1] = -0.339981043584856f;
            } else if (getGx() == 5) {
                Gauss = new double[5][2];
                Gauss[0][0] = 0.236926885056189f;
                Gauss[1][0] = 0.236926885056189f;
                Gauss[2][0] = 0.478628670499366f;
                Gauss[3][0] = 0.478628670499366f;
                Gauss[4][0] = 0.568888888888889f;

                Gauss[0][1] = 0.906179845938664f;
                Gauss[1][1] = -0.906179845938664f;
                Gauss[2][1] = 0.538469310105683f;
                Gauss[3][1] = -0.538469310105683f;
                Gauss[4][1] = 0.000000000000000f;
            }
            return Gauss;
        }

        public int[][] matrixElement() {
            int[][] element = new int[getNx()][getNum_nod()]; // getNx() is number of element ne
            for (int e = 0; e < getNx(); e++) {
                if (getNum_nod() == 2) {
                    element[e][0] = e + 1;
                    element[e][1] = e + 2;
                } else if (getNum_nod() == 3) {
                    element[e][0] = 2 * e + 1;
                    element[e][1] = 2 * e + 2;
                    element[e][2] = 2 * e + 3;
                } else if (getNum_nod()== 4) {
                    element[e][0] = 3 * e + 1;
                    element[e][1] = 3 * e + 2;
                    element[e][2] = 3 * e + 3;
                    element[e][3] = 3 * e + 4;
                } else {
                    return null;
                }
            }
            return element;
        }

        public double[] coordElement(int e, int[][] element, double[] gcoord) {
            double[] coord = new double[getNum_nod()];
            for (int i = 0; i < getNum_nod(); i++) {
                coord[i] = gcoord[element[e][i] - 1];
            }
            return coord;
        }

        public int[] getElement(int e) {
            int[] sctrB = new int[getNum_nod()];
            int[][] element = matrixElement();
            System.arraycopy(element[e], 0, sctrB, 0, getNum_nod());
            return sctrB;
        }

        public double[][] lagrangeBasis(double point) {
            double[][] Lagrange = new double[getNum_nod()][2];
            double xi = point;
            if (getNum_nod() == 2) {
                //N
                Lagrange[0][0] = 1/2f - xi/2;
                Lagrange[1][0] = 1/2f + xi/2;
                //dNdxi
                Lagrange[0][1] = -1/2f;
                Lagrange[1][1] = 1/2f;
            } else if (getNum_nod() == 3) {
                //N
                Lagrange[0][0] = (1 - xi) * xi / (-2);
                Lagrange[1][0] = 1 - xi * xi;
                Lagrange[2][0] = (1 + xi) * xi / 2;
                //dNdxi
                Lagrange[0][1] = xi - 1/2f;
                Lagrange[1][1] = -2 * xi;
                Lagrange[2][1] = xi + 1/2f;
            } else if (getNum_nod() == 4) {
                //N
                Lagrange[0][0] = -(9 * xi * xi * xi) / 16 + (9 * xi * xi) / 16 + xi / 16 - 1 / 16f;
                Lagrange[1][0] = (27 * xi * xi * xi) / 16 - (9 * xi * xi) / 16 - (27 * xi) / 16 + 9 / 16f;
                Lagrange[2][0] = (27 * xi) / 16 - (9 * xi * xi) / 16 - (27 * xi * xi * xi) / 16 + 9 / 16f;
                Lagrange[3][0] = (9 * xi * xi * xi) / 16 + (9 * xi * xi) / 16 - xi / 16 - 1 / 16f;
                //dNdxi
                Lagrange[0][1] = (9 * xi) / 8 - (27 * xi * xi) / 16 + 1 / 16f;
                Lagrange[1][1] = (81 * xi * xi) / 16 - (9 * xi) / 8 - 27 / 16f;
                Lagrange[2][1] = 27 / 16f - (81 * xi * xi) / 16 - (9 * xi) / 8;
                Lagrange[3][1] = (27 * xi * xi) / 16 + (9 * xi) / 8 - 1 / 16f;
            }
            return Lagrange;
        }
         //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Create stiffness matrix and the left hand side of stiffness matrix">
        public double[][] find_N_divN(double invJ, double[][] Lagrange) {
            double[][] NdivN = new double[getNum_nod()][2];
            for (int i = 0; i < getNum_nod(); i++) {
                //N
                NdivN[i][0] = Lagrange[i][0];
                //divN
                NdivN[i][1] = Lagrange[i][1] * invJ;
            }
            return NdivN;
        }

        public double[][] find_A(int q, double[][] A, double[][] NdivN, double[][] Gauss, double detJ) {   
            if (q == 0) {
                // init the local matrix
                A = initmatrix(getNum_nod(), getNum_nod());
            }
            double W = Gauss[q][0];
            // Computing matrix A
            for (int i = 0; i < getNum_nod(); i++) {
                for (int j = 0; j < getNum_nod(); j++) {
                    A[i][j] += (NdivN[i][1] * NdivN[j][1] + getAlpha()*(NdivN[i][0] * NdivN[j][1]) + getBeta()*(NdivN[i][0] * NdivN[j][0])) * W * detJ;
                }
            }
            return A;
        }

        public double[] find_b(int q, double[] b, double[][] NdivN, double[][] Gauss, double[] f, double detJ) {
            double W = Gauss[q][0];
            if (q == 0) {
                // init the local matrix
                b = initvector(getNum_nod());
            }
            double[][] temp = new double[getNum_nod()][getNum_nod()];
            for (int i = 0; i < getNum_nod(); i++) {
                for (int j = 0; j < getNum_nod(); j++) {
                    temp[i][j] = NdivN[i][0] * NdivN[j][0];
                }
            }

            // Computing vector b
            for (int i = 0; i < getNum_nod(); i++) {
                double sum = 0;
                for (int j = 0; j < getNum_nod(); j++) {
                    sum += temp[i][j] * f[j];
                }
                b[i] += sum * W * detJ;
            }
            return b;
        }

        public double[][] create_K(int e, int[][] element, double[][] A, double[][] K) {
            if (e == 0) {
                //init the stiffness matrix K 
                int nn = nodeX();
                K = initmatrix(nn, nn);
            }
            switch (getNum_nod()) {
                case 2: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    K[i][i] = K[i][i] + A[0][0];
                    K[i][j] = K[i][j] + A[0][1];
                    K[j][i] = K[j][i] + A[1][0];
                    K[j][j] = K[j][j] + A[1][1];

                    break;
                }
                case 3: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    K[i][i] = K[i][i] + A[0][0];
                    K[i][j] = K[i][j] + A[0][1];
                    K[i][k] = K[i][k] + A[0][2];

                    K[j][i] = K[j][i] + A[1][0];
                    K[j][j] = K[j][j] + A[1][1];
                    K[j][k] = K[j][k] + A[1][2];

                    K[k][i] = K[k][i] + A[2][0];
                    K[k][j] = K[k][j] + A[2][1];
                    K[k][k] = K[k][k] + A[2][2];
                    break;
                }
                case 4: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    int h = element[e][3] - 1;
                    K[i][i] = K[i][i] + A[0][0];
                    K[i][j] = K[i][j] + A[0][1];
                    K[i][k] = K[i][k] + A[0][2];
                    K[i][h] = K[i][h] + A[0][3];

                    K[j][i] = K[j][i] + A[1][0];
                    K[j][j] = K[j][j] + A[1][1];
                    K[j][k] = K[j][k] + A[1][2];
                    K[j][h] = K[j][h] + A[1][3];

                    K[k][i] = K[k][i] + A[2][0];
                    K[k][j] = K[k][j] + A[2][1];
                    K[k][k] = K[k][k] + A[2][2];
                    K[k][h] = K[k][h] + A[2][3];

                    K[h][i] = K[h][i] + A[3][0];
                    K[h][j] = K[h][j] + A[3][1];
                    K[h][k] = K[h][k] + A[3][2];
                    K[h][h] = K[h][h] + A[3][3];
                    break;
                }
            }
            return K;
        }

        public double[] create_F(int e, int[][] element, double[] b, double[] F) {
            if (e == 0) {
                //init the left hand side of the stiffness matrix   
                F = initvector(nodeX());
            }
            switch (getNum_nod()) {
                case 2: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;

                    F[i] = F[i] + b[0];
                    F[j] = F[j] + b[1];

                    break;
                }
                case 3: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    F[i] = F[i] + b[0];
                    F[j] = F[j] + b[1];
                    F[k] = F[k] + b[2];
                    break;
                }
                case 4: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    int h = element[e][3] - 1;
                    F[i] = F[i] + b[0];
                    F[j] = F[j] + b[1];
                    F[k] = F[k] + b[2];
                    F[h] = F[h] + b[3];
                    break;
                }
            }
            return F;
        }
      //</editor-fold>    

        //<editor-fold defaultstate="collapsed" desc="Boundary problem">

        public double[][] applyBoundary(double[][] K) {
            if (getTypeBoundary().compareTo("DIRICHLET") == 0) {
                for (int i = 0; i < nodeX(); i++) {
                    K[0][i] = 0;
                    K[i][0] = 0;
                    K[nodeX() - 1][i] = 0;
                    K[i][nodeX() - 1] = 0;
                }
                K[0][0] = 1;
                K[nodeX() - 1][nodeX() - 1] = 1;
            }
            return K;
        }

        public double[] applyBoundary(double[]bval, double[][] K, double[] F,double lampda1, double lampda2) {
            if (getTypeBoundary().compareTo("DIRICHLET") == 0) {
                int[] bnod = {1,nodeX()}; 
                int size = 2;
                for (int i = 0; i < size; i++) {
                    int temp = bnod[i] - 1;
                    for (int j = 0; j < nodeX(); j++) {
                        F[j] -= K[j][temp] * bval[i];
                    }
                    F[temp] = bval[i];

                    for (int j = 0; j < nodeX(); j++) {
                        K[temp][j] = 0;
                        K[j][temp] = 0;
                    }
                    K[temp][temp] = 1;
                }
            }
            else if (getTypeBoundary().compareTo("NEUMANN") == 0){  
                F[0]         -=  bval[0];
                F[nodeX()-1] +=  bval[1];
            }
            else if (getTypeBoundary().compareTo("ROBIN") == 0){
                K[0][0] -= lampda1;
                K[nodeX()-1][nodeX()-1] +=  lampda2;
                F[0] -= bval[0];
                F[nodeX()-1] +=  bval[1];
            }
            return F;
        }
        //</editor-fold>

        //Solve equation using LU factorization
        @Override
        public double[] solveEquation(double[][] A, double[] b) {
            int n = A.length;
            double[][] L = initmatrix(n,n);
            double[][] U = initmatrix(n,n);
            LU_factor(A,L,U);
            double[]y = Forward(L,b); // for L
            double[]x = Backward(U,y);// for U
            return x;
        }
    }
    
    class FEM2D extends FEM implements myFunc{
        private int num_nod;


        // contructor parabolic 2D
        public FEM2D(int nx,int ny,int nt,float dt,float xa,float xb,float ya, float yb,float kappa, float alpha,float beta,int Gx, int Gy, String typeBoundary, String typeElement,String typeScheme){
            super(nx,ny,nt,dt,xa,xb,ya,yb,kappa,alpha,beta,Gx,Gy,typeBoundary,typeElement,typeScheme);

        }
        // contructor elliptic 2D
        public FEM2D(int nx,int ny,float xa,float xb,float ya, float yb,float alpha,float beta,int Gx, int Gy, String typeBoundary, String typeElement){
            super(nx,ny,xa,xb,ya,yb,alpha,beta,Gx,Gy,typeBoundary,typeElement);

        }
        
        public void setNum_nod(int num_nod) {
        this.num_nod = num_nod;
        }

        public int getNum_nod() {
            return num_nod;
        }
        //<editor-fold defaultstate="collapsed" desc="Init something">
    

    public int numNode( String typeElement) {

        if (typeElement.compareTo("T3") == 0)
            num_nod = 3;
        if (typeElement.compareTo("T6") == 0)
            num_nod = 6;
        if (typeElement.compareTo("Q4") == 0) 
            num_nod = 4;
        if (typeElement.compareTo("Q8") == 0) 
            num_nod = 8;    
        setNum_nod(num_nod);
        return getNum_nod();
    }
    @Override
    public double[] initvector(int n){
        double[]u = new double [n];
        for(int i = 0; i<n;i++)
            u[i] = 0;
        return u;
    }
    @Override
    public double[][] initmatrix(int m, int n){
        double[][]A = new double [m][n];
        for(int i = 0; i<m;i++){
            for (int j = 0; j<n;j++)
                A[i][j] = 0;
        }
        return A;
    }
    //</editor-fold>
    
        //<editor-fold defaultstate="collapsed" desc="Gauss and matrix element">
        public double[][] quadrature1D(int Ngauss){
            double[][] Gauss= new double[1][1];
            if (Ngauss > 5) 
                Ngauss = 5;
            
            if (Ngauss == 1){
                Gauss = new double[1][2];
                Gauss[0][0] = 2.000000000000000f;
                Gauss[0][1] = 0.000000000000000f;
            }
            else if (Ngauss == 2){
                Gauss = new double[2][2];
                Gauss[0][0] = 1.000000000000000f; 
                Gauss[1][0] = 1.000000000000000f;

                Gauss[0][1] = 0.577350269189626f; 
                Gauss[1][1] = -0.577350269189626f;
            }
            else if (Ngauss == 3){
                Gauss = new double[3][2];
                Gauss[0][0] = 0.555555555555556f; 
                Gauss[1][0] = 0.555555555555556f;
                Gauss[2][0] = 0.888888888888889f;

                Gauss[0][1] = 0.774596669241483f; 
                Gauss[1][1] = -0.774596669241483f;
                Gauss[2][1] = 0.000000000000000f;
            }
            else if (Ngauss == 4){
                Gauss = new double[4][2];
                Gauss[0][0] = 0.347854845137454f; 
                Gauss[1][0] = 0.347854845137454f;
                Gauss[2][0] = 0.652145154862546f;
                Gauss[3][0] = 0.652145154862546f;

                Gauss[0][1] = 0.861134311594053f; 
                Gauss[1][1] = -0.861134311594053f;
                Gauss[2][1] = 0.339981043584856f;
                Gauss[3][1] = -0.339981043584856f;
            }  
            else if (Ngauss == 5){
                Gauss = new double[5][2];
                Gauss[0][0] = 0.236926885056189f; 
                Gauss[1][0] = 0.236926885056189f;
                Gauss[2][0] = 0.478628670499366f;
                Gauss[3][0] = 0.478628670499366f;
                Gauss[4][0] = 0.568888888888889f;

                Gauss[0][1] = 0.906179845938664f; 
                Gauss[1][1] = -0.906179845938664f;
                Gauss[2][1] = 0.538469310105683f;
                Gauss[3][1] = -0.538469310105683f;
                Gauss[4][1] = 0.000000000000000f;
            } 
            return Gauss;
        }

        public double[][] quadrature2D(){
            double[][] Gauss = new double[1][1];
            if (getTypeElement().compareTo("T3")== 0 ||getTypeElement().compareTo("T6")== 0){
                int quadorder = getGx();
                if (quadorder > 5){
                    System.out.println(" Quadrature order too high for triangular quadrature");
                    quadorder = 5;
                }
                 if (quadorder == 1){
                    Gauss = new double[1][3];
                    Gauss[0][0] = 0.5f;
                    Gauss[0][1] = 0.3333333333333f;
                    Gauss[0][2] = 0.3333333333333f;
                }
                else if (quadorder == 2){
                    Gauss = new double[3][3];
                    Gauss[0][0]= 0.1666666666667f;
                    Gauss[1][0]= 0.1666666666667f;
                    Gauss[2][0]= 0.1666666666667f;

                    Gauss[0][1] = 0.1666666666667f;
                    Gauss[1][1] = 0.1666666666667f;
                    Gauss[2][1] = 0.6666666666667f;
                    Gauss[0][2] = 0.1666666666667f;
                    Gauss[1][2] = 0.1666666666667f;
                    Gauss[2][2] = 0.6666666666667f;
                }
                else if((quadorder > 2) && (quadorder <= 5)){
                    Gauss = new double[7][3];
                    Gauss[0][0] = 0.1259391805448f/2; 
                    Gauss[1][0] = 0.1259391805448f/2; 
                    Gauss[2][0] = 0.1259391805448f/2; 
                    Gauss[3][0] = 0.1323941527885f/2;
                    Gauss[4][0] = 0.1323941527885f/2;
                    Gauss[5][0] = 0.1323941527885f/2;
                    Gauss[6][0] = 0.2250000000000f/2;

                    Gauss[0][1] = 0.1012865073235f;
                    Gauss[0][2] = 0.1012865073235f;
                    Gauss[1][1] = 0.7974269853531f;
                    Gauss[1][2] = 0.1012865073235f;
                    Gauss[2][1] = 0.1012865073235f;
                    Gauss[2][2] = 0.7974269853531f;
                    Gauss[3][1] = 0.4701420641051f;
                    Gauss[3][2] = 0.0597158717898f;
                    Gauss[4][1] = 0.4701420641051f;
                    Gauss[4][2] = 0.470142064105f;
                    Gauss[5][1] = 0.0597158717898f;
                    Gauss[5][2] = 0.4701420641051f;
                    Gauss[6][1] = 0.3333333333333f;
                    Gauss[6][2] = 0.3333333333333f;
                }
            }
            else if (getTypeElement().compareTo("Q4")== 0 ||getTypeElement().compareTo("Q8")== 0){
                int quadorderX = getGx();
                int quadorderY = getGy();
                if (quadorderX > 5)
                    quadorderX =5;
                
                else if (quadorderY > 5)
                    quadorderY =5;
                
                int Ngauss;
                if (quadorderX >quadorderY)
                    Ngauss = quadorderX;
                else
                    Ngauss = quadorderY;
                Gauss = new double[Ngauss][4];
                double[][]GaussX = quadrature1D(quadorderX);
                double[][]GaussY = quadrature1D(quadorderY);

                for (int i = 0;i<quadorderX;i++){
                    Gauss[i][1] = GaussX[i][1];// point
                    Gauss[i][0] = GaussX[i][0]; // weight;
                }
                for (int i = 0;i<quadorderY;i++){
                    Gauss[i][3] = GaussY[i][1];// point
                    Gauss[i][2] = GaussY[i][0]; // weight;
                }            
            }
            return Gauss;
        }
        public int[][] matrixElement() {
            int nx = getNx();
            int ny = getNy();
            int[][] element = new int[numElement()][getNum_nod()];
            if (getTypeElement().compareTo("T3") == 0) {
                for (int j = 1; j <= ny; j++) {
                    for (int i = 1; i <= nx; i++) {
                        int ne1 = (j - 1) * (2 * nx) + 2 * (i - 1);
                        element[ne1][0] = j * (nx + 1) + i;
                        element[ne1][1] = (j - 1) * (nx + 1) + i;
                        element[ne1][2] = j * (nx + 1) + 1 + i;
                        int ne2 = ne1 + 1;
                        element[ne2][0] = (j - 1) * (nx + 1) + 1 + i;
                        element[ne2][1] = j * (nx + 1) + 1 + i;
                        element[ne2][2] = (j - 1) * (nx + 1) + i;
                    }
                }
            } else if (getTypeElement().compareTo("T6") == 0) {
                for (int j = 1; j <= ny; j++) {
                    for (int i = 1; i <= nx; i++) {
                        int ne1 = (j - 1) * (2 * nx) + 2 * (i - 1);
                        element[ne1][0] = 2 * i + 4 * nx + (4 * nx + 2) * (j - 1) + 1;
                        element[ne1][1] = 2 * (2 * nx + 1) * (j - 1) + 2 * (i - 1) + 1;
                        element[ne1][2] = 2 * i + 4 * nx + (4 * nx + 2) * (j - 1) + 3;
                        element[ne1][3] = 2 * i + 2 * nx + (4 * nx + 2) * (j - 1) + 1;
                        element[ne1][4] = 2 * i + 4 * nx + (4 * nx + 2) * (j - 1) + 2;
                        element[ne1][5] = 2 * i + 2 * nx + (4 * nx + 2) * (j - 1);
                        int ne2 = ne1 + 1;
                        element[ne2][0] = 2 * (2 * nx + 1) * (j - 1) + 2 * (i - 1) + 3;
                        element[ne2][1] = 2 * i + 4 * nx + (4 * nx + 2) * (j - 1) + 3;
                        element[ne2][2] = 2 * (2 * nx + 1) * (j - 1) + 2 * (i - 1) + 1;
                        element[ne2][3] = 2 * i + 2 * nx + (4 * nx + 2) * (j - 1) + 1;
                        element[ne2][4] = 2 * i + (4 * nx + 2) * (j - 1);
                        element[ne2][5] = 2 * i + 2 * nx + (4 * nx + 2) * (j - 1) + 2;
                    }
                }
            }
            else if (getTypeElement().compareTo("Q4") == 0) {
                for (int j = 1; j <= ny; j++) {
                    for (int i = 1; i <= nx; i++) {
                        int ne1 = (j - 1) * nx + i - 1;
                        element[ne1][0] = (j - 1) * (nx + 1) + i;
                        element[ne1][1] = (j - 1) * (nx + 1) + i + 1;
                        element[ne1][2] = (j - 1) * (nx + 1) + nx + 2 + i;
                        element[ne1][3] = (j - 1) * (nx + 1) + nx + 1 + i;

                    }
                } 
            }
            else if (getTypeElement().compareTo("Q8") == 0) {
                for (int j = 1; j <= ny; j++) {
                    for (int i = 1; i <= nx; i++) {
                        int ne1 = (j - 1) * nx + i - 1;
                        element[ne1][0] = (j - 1) * (3 * nx + 2) + (2 * i - 1);
                        element[ne1][1] = (j - 1) * (3 * nx + 2) + (2 * i - 1) + 1;
                        element[ne1][2] = (j - 1) * (3 * nx + 2) + (2 * i - 1) + 2;
                        element[ne1][3] = (j - 1) * (3 * nx + 2) + i + 2 * nx + 1 + 1;
                        element[ne1][4] = (j - 1) * (3 * nx + 2) + (2 * i - 1) + 2 + 3 * nx + 2;
                        element[ne1][5] = (j - 1) * (3 * nx + 2) + (2 * i - 1) + 3 * nx + 2 + 1;
                        element[ne1][6] = (j - 1) * (3 * nx + 2) + (2 * i - 1) + 3 * nx + 2;
                        element[ne1][7] = (j - 1) * (3 * nx + 2) + i + 2 * nx + 1;

                    }
                }
            }
            return element;
    }
        public double[][] coordElement(int e,int[][]element,double[][] gcoord){
            double[][] uv = new double[getNum_nod()][2];
            for (int i = 0; i < getNum_nod(); i++) {
                int temp = element[e][i];
                uv[i][0] = gcoord[temp - 1][0];  
                uv[i][1] = gcoord[temp - 1][1];
            }
            return uv;
        }
        public double[] getPointGauss(int q){
            double[][] Gauss = quadrature2D();
            double[] point = new double[2];
            if(getTypeElement().compareTo("T3")==0 || getTypeElement().compareTo("T6")==0){
                point[0] = Gauss[q][1]; // POINT X
                point[1] = Gauss[q][2];// POINT Y
            }
            else if (getTypeElement().compareTo("Q4")==0 || getTypeElement().compareTo("Q8")==0){
                point[0] = Gauss[q][1]; // POINT X
                point[1] = Gauss[q][3];// POINT Y
            }
            else
                return null;
            
            return point;
        }
        public int[] getElement(int e){
            int[] sctrB = new int[getNum_nod()];
            int[][] element = matrixElement();
            System.arraycopy(element[e], 0, sctrB, 0, getNum_nod());
            return sctrB;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Jacobi and Lagrange">


        public double[][] jacobiMatrix(double[][] uv, double[] point) {
            double xi = point[0];
            double eta = point[1];
            double[][]mt = new double[2][2];
            if (getTypeElement().compareTo("T3")== 0 ){
                double u1 = uv[0][0], u2 = uv[1][0], u3 = uv[2][0];
                double v1 = uv[0][1], v2 = uv[1][1], v3 = uv[2][1];

                mt[0][0] = u2 - u1;
                mt[0][1] = u3 - u1;
                mt[1][0] = v2 - v1;
                mt[1][1] = v3 - v1;
            }
                else if(getTypeElement().compareTo("T6")== 0){
                double u1 = uv[0][0], u2 = uv[1][0], u3 = uv[2][0], u4 = uv[3][0], u5 = uv[4][0], u6 = uv[5][0];
                double v1 = uv[0][1], v2 = uv[1][1], v3 = uv[2][1], v4 = uv[3][1], v5 = uv[4][1], v6 = uv[5][1];
                mt[0][0] = u2*(4*xi - 1) + 4*eta*u4 - 4*eta*u5 + u1*(4*eta + 4*xi - 3) - u6*(4*eta + 8*xi - 4);
                mt[0][1] = 4*u4*xi - 4*u6*xi + u1*(4*eta + 4*xi - 3) - u5*(8*eta + 4*xi - 4) + u3*(4*eta - 1);
                mt[1][0] = v2*(4*xi - 1) + 4*eta*v4 - 4*eta*v5 + v1*(4*eta + 4*xi - 3) - v6*(4*eta + 8*xi - 4);
                mt[1][1] = 4*xi*v4 - 4*xi*v6 + v1*(4*eta + 4*xi - 3) - v5*(8*eta + 4*xi - 4) + v3*(4*eta - 1); 
            }
            else if (getTypeElement().compareTo("Q4") == 0) {
                double u1 = uv[0][0], u2 = uv[1][0], u3 = uv[2][0], u4 = uv[3][0];
                double v1 = uv[0][1], v2 = uv[1][1], v3 = uv[2][1], v4 = uv[3][1];

                mt[0][0] = ((eta - 1) * (u1 - u2) + (eta + 1) * (u3 - u4)) / 4;
                mt[0][1] = ((xi - 1) * (u1 - u4) + (xi + 1) * (u3 - u2)) / 4;
                mt[1][0] = ((eta - 1) * (v1 - v2) + (eta + 1) * (v3 - v4)) / 4;
                mt[1][1] = ((xi - 1) * (v1 - v4) + (xi + 1) * (v3 - v2)) / 4;
            } else if (getTypeElement().compareTo("Q8") == 0) {
                
                double u1 = uv[0][0], u2 = uv[1][0], u3 = uv[2][0], u4 = uv[3][0], u5 = uv[4][0],u6 = uv[5][0], u7 = uv[6][0], u8 = uv[7][0];
                double v1 = uv[0][1], v2 = uv[1][1], v3 = uv[2][1], v4 = uv[3][1], v5 = uv[4][1],v6 = uv[5][1], v7 = uv[6][1], v8 = uv[7][1];

                mt[0][0] = u8*(eta*eta/2 - 1/2f) - u4*(eta*eta/2 - 1/2f) - u1*(((eta - 1)*(eta + xi + 1))/4f  + ((eta - 1)*(xi - 1))/4f) + u5*(((eta + 1)*(eta + xi - 1))/4f + ((eta + 1)*(xi + 1))/4f)
                           + u3*(((eta - 1)*(eta - xi + 1))/4f - ((eta - 1)*(xi + 1))/4f) + u7*(((eta + 1)*(xi - eta + 1))/4f + ((eta + 1)*(xi - 1))/4f) + u2*xi*(eta - 1) - u6*xi*(eta + 1);
                mt[0][1] = u3*(((xi + 1)*(eta - xi + 1))/4f + ((eta - 1)*(xi + 1))/4f) + u7*(((xi - 1)*(xi - eta + 1))/4f - ((eta + 1)*(xi - 1))/4f) + u2*(xi*xi/2 - 1/2f) - u6*(xi*xi/2 - 1/2f) 
                          - u1*(((xi - 1)*(eta + xi + 1))/4f + ((eta - 1)*(xi - 1))/4f) + u5*(((xi + 1)*(eta + xi - 1))/4f + ((eta + 1)*(xi + 1))/4f) - (eta*u4*(2*xi + 2))/2f + (eta*u8*(2*xi - 2))/2f;
                mt[1][0] = v8*(eta*eta/2 - 1/2f) - v4*(eta*eta/2 - 1/2f) - v1*(((eta - 1)*(eta + xi + 1))/4 + ((eta - 1)*(xi - 1))/4f) + v5*(((eta + 1)*(eta + xi - 1))/4f + ((eta + 1)*(xi + 1))/4f)
                          + v3*(((eta - 1)*(eta - xi + 1))/4f - ((eta - 1)*(xi + 1))/4f) + v7*(((eta + 1)*(xi - eta + 1))/4f + ((eta + 1)*(xi - 1))/4f) + xi*v2*(eta - 1) - xi*v6*(eta + 1);
                mt[1][1] = v3*(((xi + 1)*(eta - xi + 1))/4f + ((eta - 1)*(xi + 1))/4f) + v7*(((xi - 1)*(xi - eta + 1))/4f - ((eta + 1)*(xi - 1))/4f) + v2*(xi*xi/2 - 1/2f) - v6*(xi*xi/2 - 1/2f) 
                        - v1*(((xi - 1)*(eta + xi + 1))/4f+ ((eta - 1)*(xi - 1))/4f) + v5*(((xi + 1)*(eta + xi - 1))/4f + ((eta + 1)*(xi + 1))/4f) - (eta*v4*(2*xi + 2))/2f + (eta*v8*(2*xi - 2))/2f;
            }
            return mt;
        }
        public double detJacobi(double[][] mt){
            return mt[0][0]*mt[1][1] - mt[1][0]*mt[0][1];
        }
        public double[][] invertJacobi(double[][] Jacobi, double detJ) {
            double[][] invJ = new double[2][2];
            invJ[0][0] = Jacobi[1][1] / detJ;
            invJ[0][1] = -Jacobi[0][1] / detJ;
            invJ[1][0] = -Jacobi[1][0] / detJ;
            invJ[1][1] = Jacobi[0][0] / detJ;
            return invJ;
        }
        public double[][] lagrangeBasis(double[] point) {
            double[][] Lagrange = new double[getNum_nod()][3];
            double xi = point[0];    
            double eta = point[1];
            if (getTypeElement().compareTo("T3") == 0) {
                //N
                Lagrange[0][0] = 1 - xi - eta;
                Lagrange[1][0] = xi;
                Lagrange[2][0] = eta;
                //dNdxi
                Lagrange[0][1] = -1;
                Lagrange[1][1] = 1;
                Lagrange[2][1] = 0;
                //dNdeta
                Lagrange[0][2] = -1;
                Lagrange[1][2] = 0;
                Lagrange[2][2] = 1;
            } else if (getTypeElement().compareTo("T6") == 0) {
                //N
                Lagrange[0][0] = 2 * xi * xi + 2 * eta * eta + 4 * xi * eta - 3 * xi - 3 * eta + 1;
                Lagrange[1][0] = 2 * xi * xi - xi;
                Lagrange[2][0] = 2 * eta * eta - eta;
                Lagrange[3][0] = 4 * xi * eta;
                Lagrange[4][0] = 4 * eta - 4 * xi * eta - 4 * eta * eta;
                Lagrange[5][0] = 4 * xi - 4 * xi * eta - 4 * xi * xi;
                //dNdxi
                Lagrange[0][1] = 4 * eta + 4 * xi - 3;
                Lagrange[1][1] = 4 * xi - 1;
                Lagrange[2][1] = 0;
                Lagrange[3][1] = 4 * eta;
                Lagrange[4][1] = -4 * eta;
                Lagrange[5][1] = 4 - 8 * xi - 4 * eta;
                //dNdeta
                Lagrange[0][2] = 4 * eta + 4 * xi - 3;
                Lagrange[1][2] = 0;
                Lagrange[2][2] = 4 * eta - 1;
                Lagrange[3][2] = 4 * xi;
                Lagrange[4][2] = 4 - 4 * xi - 8 * eta;
                Lagrange[5][2] = -4 * xi;
            }
            else if (getTypeElement().compareTo("Q4") == 0) {
                //N
                Lagrange[0][0] = ((1 - xi) * (1 - eta) )/ 4;
                Lagrange[1][0] = ((1 + xi) * (1 - eta) )/ 4;
                Lagrange[2][0] = ((1 + xi) * (1 + eta) )/ 4;
                Lagrange[3][0] = ((1 - xi) * (1 + eta) )/ 4;
                //dNdxi
                Lagrange[0][1] = -(1 - eta) / 4;
                Lagrange[1][1] = (1 - eta) / 4;
                Lagrange[2][1] = (1 + eta) / 4;
                Lagrange[3][1] = -(1 + eta) / 4;
                //dNdeta
                Lagrange[0][2] = -(1 - xi) / 4;
                Lagrange[1][2] = -(1 + xi) / 4;
                Lagrange[2][2] = (1 + xi) / 4;
                Lagrange[3][2] = (1 - xi) / 4;

            } else if (getTypeElement().compareTo("Q8") == 0) {
                //N
                Lagrange[0][0] = (1 - xi) * (1 - eta) * (-1 - xi - eta) / 4;
                Lagrange[1][0] = (1 - xi * xi) * (1 - eta) / 2;
                Lagrange[2][0] = (1 + xi) * (1 - eta) * (-1 + xi - eta) / 4;
                Lagrange[3][0] = (1 + xi) * (1 - eta * eta) / 2;
                Lagrange[4][0] = (1 + xi) * (1 + eta) * (-1 + xi + eta) / 4;
                Lagrange[5][0] = (1 - xi * xi) * (1 + eta) / 2;
                Lagrange[6][0] = (1 - xi) * (1 + eta) * (-1 - xi + eta) / 4;
                Lagrange[7][0] = (1 - xi) * (1 - eta * eta) / 2;
                //dNdxi
                Lagrange[0][1] =  - ((eta - 1)*(eta + xi + 1))/4 - ((eta - 1)*(xi - 1))/4;
                Lagrange[1][1] = xi*(eta - 1);
                Lagrange[2][1] =  ((eta - 1)*(eta - xi + 1))/4 - ((eta - 1)*(xi + 1))/4;
                Lagrange[3][1] =  1/2f - eta*eta/2;
                Lagrange[4][1] = ((eta + 1)*(eta + xi - 1))/4 + ((eta + 1)*(xi + 1))/4;
                Lagrange[5][1] = -xi * (eta + 1);
                Lagrange[6][1] = ((eta + 1)*(xi - eta + 1))/4 + ((eta + 1)*(xi - 1))/4;
                Lagrange[7][1] =  eta*eta/2 - 1/2f;
                //dNdeta
                Lagrange[0][2] = - ((xi - 1)*(eta + xi + 1))/4 - ((eta - 1)*(xi - 1))/4;
                Lagrange[1][2] = xi*xi/2 - 1/2f;
                Lagrange[2][2] = ((xi + 1)*(eta - xi + 1))/4 + ((eta - 1)*(xi + 1))/4;
                Lagrange[3][2] =   -(eta*(2*xi + 2))/2;
                Lagrange[4][2] =  ((xi + 1)*(eta + xi - 1))/4 + ((eta + 1)*(xi + 1))/4;
                Lagrange[5][2] =  1/2f - xi*xi/2;
                Lagrange[6][2] = ((xi - 1)*(xi - eta + 1))/4 - ((eta + 1)*(xi - 1))/4;
                Lagrange[7][2] = (eta*(2*xi - 2))/2;
            }
            return Lagrange;
        }
        public double[][] find_N_divN(double[][] invJ, double[][] Lagrange) {
            double[][] resuft = new double[getNum_nod()][3];
            for (int i = 0; i < getNum_nod(); i++) {
                resuft[i][0] = Lagrange[i][0];
                resuft[i][1] = Lagrange[i][1] * invJ[0][0] + Lagrange[i][2] * invJ[1][0];
                resuft[i][2] = Lagrange[i][1] * invJ[0][1] + Lagrange[i][2] * invJ[1][1];

            }
            return resuft;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Create stiffness matrix and the left hand side of stiffness matrix">


        public double[][] find_A(int q,double[][]A,double[][] NdivN, double[][]Gauss,double detJ){
            if (q == 0 ){
                 // init the local matrix
                A =initmatrix(getNum_nod(), getNum_nod());
            }
            double W = Gauss[q][0];
            // Computing matrix A
            for (int i = 0;i<getNum_nod();i++){
                    for (int j = 0;j<getNum_nod();j++){
                            A[i][j] += (NdivN[i][1]*NdivN[j][1]+NdivN[i][2]*NdivN[j][2] + getAlpha()*(NdivN[i][0]*NdivN[j][1] + NdivN[i][0]*NdivN[j][2]) + getBeta()*(NdivN[i][0]*NdivN[j][0]))*W*detJ;
                    }
            }
            return A;
        }
        public double[] find_b(int q,double[]b, double[][] NdivN, double[][] Gauss, double[] f, double detJ) {
            double W = Gauss[q][0];
            if (q == 0 ){
                 // init the local matrix
                 b  = initvector(getNum_nod());
            }
            double[][] temp = new double[getNum_nod()][getNum_nod()];
            for (int i = 0;i<getNum_nod();i++){
                    for (int j = 0;j<getNum_nod();j++){
                            temp[i][j] = NdivN[i][0]*NdivN[j][0];
                    }
            }

            // Computing vector b
            for (int i = 0;i<getNum_nod();i++){
                    double sum = 0;
                    for (int j = 0;j<getNum_nod();j++)
                            sum +=temp[i][j]*f[j];
                    b[i] += sum*W*detJ;
            }
            return b;
        }
        public double[][] create_K(int e,int[][]element, double[][] A, double[][] K) {
            if (e == 0){
            //init the stiffness matrix K 
                int nn = numberNode();
                K = initmatrix(nn, nn);
            }
            switch (getNum_nod()) {
                case 3: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    K[i][i] = K[i][i] + A[0][0];
                    K[i][j] = K[i][j] + A[0][1];
                    K[i][k] = K[i][k] + A[0][2];
                    K[j][i] = K[j][i] + A[1][0];
                    K[j][j] = K[j][j] + A[1][1];
                    K[j][k] = K[j][k] + A[1][2];
                    K[k][i] = K[k][i] + A[2][0];
                    K[k][j] = K[k][j] + A[2][1];
                    K[k][k] = K[k][k] + A[2][2];
                    break;
                }
                case 6: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    int h = element[e][3] - 1;
                    int l = element[e][4] - 1;
                    int p = element[e][5] - 1;
                    K[i][i] = K[i][i] + A[0][0];
                    K[i][j] = K[i][j] + A[0][1];
                    K[i][k] = K[i][k] + A[0][2];
                    K[i][h] = K[i][h] + A[0][3];
                    K[i][l] = K[i][l] + A[0][4];
                    K[i][p] = K[i][p] + A[0][5];

                    K[j][i] = K[j][i] + A[1][0];
                    K[j][j] = K[j][j] + A[1][1];
                    K[j][k] = K[j][k] + A[1][2];
                    K[j][h] = K[j][h] + A[1][3];
                    K[j][l] = K[j][l] + A[1][4];
                    K[j][p] = K[j][p] + A[1][5];

                    K[k][i] = K[k][i] + A[2][0];
                    K[k][j] = K[k][j] + A[2][1];
                    K[k][k] = K[k][k] + A[2][2];
                    K[k][h] = K[k][h] + A[2][3];
                    K[k][l] = K[k][l] + A[2][4];
                    K[k][p] = K[k][p] + A[2][5];

                    K[h][i] = K[h][i] + A[3][0];
                    K[h][j] = K[h][j] + A[3][1];
                    K[h][k] = K[h][k] + A[3][2];
                    K[h][h] = K[h][h] + A[3][3];
                    K[h][l] = K[h][l] + A[3][4];
                    K[h][p] = K[h][p] + A[3][5];

                    K[l][i] = K[l][i] + A[4][0];
                    K[l][j] = K[l][j] + A[4][1];
                    K[l][k] = K[l][k] + A[4][2];
                    K[l][h] = K[l][h] + A[4][3];
                    K[l][l] = K[l][l] + A[4][4];
                    K[l][p] = K[l][p] + A[4][5];

                    K[p][i] = K[p][i] + A[5][0];
                    K[p][j] = K[p][j] + A[5][1];
                    K[p][k] = K[p][k] + A[5][2];
                    K[p][h] = K[p][h] + A[5][3];
                    K[p][l] = K[p][l] + A[5][4];
                    K[p][p] = K[p][p] + A[5][5];
                    break;
                }
                case 4:{
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    int h = element[e][3] - 1;
                    K[i][i] = K[i][i] + A[0][0];
                    K[i][j] = K[i][j] + A[0][1];
                    K[i][k] = K[i][k] + A[0][2];
                    K[i][h] = K[i][h] + A[0][3];

                    K[j][i] = K[j][i] + A[1][0];
                    K[j][j] = K[j][j] + A[1][1];
                    K[j][k] = K[j][k] + A[1][2];
                    K[j][h] = K[j][h] + A[1][3];

                    K[k][i] = K[k][i] + A[2][0];
                    K[k][j] = K[k][j] + A[2][1];
                    K[k][k] = K[k][k] + A[2][2];
                    K[k][h] = K[k][h] + A[2][3];

                    K[h][i] = K[h][i] + A[3][0];
                    K[h][j] = K[h][j] + A[3][1];
                    K[h][k] = K[h][k] + A[3][2];
                    K[h][h] = K[h][h] + A[3][3];
                    break;

                }
                case 8:{
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    int h = element[e][3] - 1;
                    int l = element[e][4] - 1;
                    int p = element[e][5] - 1;
                    int q = element[e][6] - 1;
                    int r = element[e][7] - 1;

                    K[i][i] = K[i][i] + A[0][0];
                    K[i][j] = K[i][j] + A[0][1];
                    K[i][k] = K[i][k] + A[0][2];
                    K[i][h] = K[i][h] + A[0][3];
                    K[i][l] = K[i][l] + A[0][4];
                    K[i][p] = K[i][p] + A[0][5];
                    K[i][q] = K[i][q] + A[0][6];
                    K[i][r] = K[i][r] + A[0][7];

                    K[j][i] = K[j][i] + A[1][0];
                    K[j][j] = K[j][j] + A[1][1];
                    K[j][k] = K[j][k] + A[1][2];
                    K[j][h] = K[j][h] + A[1][3];
                    K[j][l] = K[j][l] + A[1][4];
                    K[j][p] = K[j][p] + A[1][5];
                    K[j][q] = K[j][q] + A[1][6];
                    K[j][r] = K[j][r] + A[1][7];

                    K[k][i] = K[k][i] + A[2][0];
                    K[k][j] = K[k][j] + A[2][1];
                    K[k][k] = K[k][k] + A[2][2];
                    K[k][h] = K[k][h] + A[2][3];
                    K[k][l] = K[k][l] + A[2][4];
                    K[k][p] = K[k][p] + A[2][5];
                    K[k][q] = K[k][q] + A[2][6];
                    K[k][r] = K[k][r] + A[2][7];

                    K[h][i] = K[h][i] + A[3][0];
                    K[h][j] = K[h][j] + A[3][1];
                    K[h][k] = K[h][k] + A[3][2];
                    K[h][h] = K[h][h] + A[3][3];
                    K[h][l] = K[h][l] + A[3][4];
                    K[h][p] = K[h][p] + A[3][5];
                    K[h][q] = K[h][q] + A[3][6];
                    K[h][r] = K[h][r] + A[3][7];

                    K[l][i] = K[l][i] + A[4][0];
                    K[l][j] = K[l][j] + A[4][1];
                    K[l][k] = K[l][k] + A[4][2];
                    K[l][h] = K[l][h] + A[4][3];
                    K[l][l] = K[l][l] + A[4][4];
                    K[l][p] = K[l][p] + A[4][5];
                    K[l][q] = K[l][q] + A[4][6];
                    K[l][r] = K[l][r] + A[4][7];

                    K[p][i] = K[p][i] + A[5][0];
                    K[p][j] = K[p][j] + A[5][1];
                    K[p][k] = K[p][k] + A[5][2];
                    K[p][h] = K[p][h] + A[5][3];
                    K[p][l] = K[p][l] + A[5][4];
                    K[p][p] = K[p][p] + A[5][5];
                    K[p][q] = K[p][q] + A[5][6];
                    K[p][r] = K[p][r] + A[5][7];  

                    K[q][i] = K[q][i] + A[6][0];
                    K[q][j] = K[q][j] + A[6][1];
                    K[q][k] = K[q][k] + A[6][2];
                    K[q][h] = K[q][h] + A[6][3];
                    K[q][l] = K[q][l] + A[6][4];
                    K[q][p] = K[q][p] + A[6][5];
                    K[q][q] = K[q][q] + A[6][6];
                    K[q][r] = K[q][r] + A[6][7];

                    K[r][i] = K[r][i] + A[7][0];
                    K[r][j] = K[r][j] + A[7][1];
                    K[r][k] = K[r][k] + A[7][2];
                    K[r][h] = K[r][h] + A[7][3];
                    K[r][l] = K[r][l] + A[7][4];
                    K[r][p] = K[r][p] + A[7][5];
                    K[r][q] = K[r][q] + A[7][6];
                    K[r][r] = K[r][r] + A[7][7];
                }
            }      
            return K;
        }
        public double[] create_F(int e,int[][]element ,double[] b, double[] F) {
            if (e==0){
                //init the left hand side of the stiffness matrix   
                F   = initvector(numberNode());
            }
            switch (getNum_nod()) {
                case 3: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    F[i] = F[i] + b[0];
                    F[j] = F[j] + b[1];
                    F[k] = F[k] + b[2];
                    break;
                }
                case 6: {
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    int h = element[e][3] - 1;
                    int l = element[e][4] - 1;
                    int p = element[e][5] - 1;
                    F[i] = F[i] + b[0];
                    F[j] = F[j] + b[1];
                    F[k] = F[k] + b[2];
                    F[h] = F[h] + b[3];
                    F[l] = F[l] + b[4];
                    F[p] = F[p] + b[5];
                    break;
                }
                case 4:{
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    int h = element[e][3] - 1;
                    F[i] = F[i] + b[0];
                    F[j] = F[j] + b[1];
                    F[k] = F[k] + b[2];
                    F[h] = F[h] + b[3];
                    break;
                }
                case 8:{
                    int i = element[e][0] - 1;
                    int j = element[e][1] - 1;
                    int k = element[e][2] - 1;
                    int h = element[e][3] - 1;
                    int l = element[e][4] - 1;
                    int p = element[e][5] - 1;
                    int q = element[e][6] - 1;
                    int r = element[e][7] - 1;
                    F[i] = F[i] + b[0];
                    F[j] = F[j] + b[1];
                    F[k] = F[k] + b[2];
                    F[h] = F[h] + b[3];
                    F[l] = F[l] + b[4];
                    F[p] = F[p] + b[5];
                    F[q] = F[q] + b[6];
                    F[r] = F[r] + b[7];
                    break;
                }   
            }
            return F;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Boundary problem">

        public double[][] applyBoundary(int[] nodebc, double[][] K)  {
            if (getTypeBoundary().compareTo("DIRICHLET") == 0) {
                int size = 2 * nodeX() + 2 * nodeY() - 4;
                for (int i = 0; i < size; i++) {
                    int temp = nodebc[i] - 1;
                    for (int j = 0; j < numberNode(); j++) {
                        K[temp][j] = 0;
                        K[j][temp] = 0;
                    }
                    K[temp][temp] = 1;
                }
            }
            return K;
        }
        
        public double[] applyBoundary(int[] nodebc, double[] valbc, double[][] K, double[] F) {
            if (getTypeBoundary().compareTo("DIRICHLET") == 0) {
                int size = 2 * nodeX() + 2 * nodeY() - 4;
                for (int i = 0; i < size; i++) {
                    int temp = nodebc[i] - 1;
                    for (int j = 0; j < numberNode(); j++) {
                        F[j] -= K[j][temp] * valbc[i];
                    }
                    F[temp] = valbc[i];

                    for (int j = 0; j < numberNode(); j++) {
                        K[temp][j] = 0;
                        K[j][temp] = 0;
                    }
                    K[temp][temp] = 1;
                }
            }
            return F;
        }
        //</editor-fold>

        //Solve equation using LU factorization
        @Override
        public double[] solveEquation(double[][] A, double[] b) {
            int n = A.length;
            double[][] L = initmatrix(n,n);
            double[][] U = initmatrix(n,n);
            LU_factor(A,L,U);
            double[]y = Forward(L,b); // for L
            double[]x = Backward(U,y);// for U
            return x;
        }
        }


    
}

