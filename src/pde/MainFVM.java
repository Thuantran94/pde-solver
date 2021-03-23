package pde;

import java.io.IOException;

public final class MainFVM extends FVM {
    private final String FUNCTION_UEXACT,FUNCTION_DIV_UEXACT,FUNCTION_F;
    private final double[][] GCOORD;
    private final float lambda1, lambda2;
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">

    // constructor  2D  parabolic
    public MainFVM(int nx , int ny, int nt,float dt, float xa, float xb, float ya, float yb, float kappa, float alpha,float beta,
            String typeBoundary, String typeElement,String typeScheme,float lambda1, float lambda2,
            String uex, String duex, String funcf,double[][]gcoord){
         super(nx, ny, nt, dt, xa, xb, ya, yb, kappa, alpha, beta, typeBoundary, typeElement, typeScheme);
        this.FUNCTION_UEXACT = uex;
        this.FUNCTION_DIV_UEXACT = duex;
        this.FUNCTION_F = funcf;
        this.GCOORD = gcoord;
        this.lambda1 = lambda1;
        this.lambda2 = lambda2;
    }
    // constructor  2D  elliptic
    public MainFVM(int nx , int ny, float xa, float xb, float ya, float yb, float alpha,float beta, String typeBoundary, String typeElement,
            float lambda1, float lambda2, String uex, String duex, String funcf,double[][]gcoord ){
        super(nx, ny, xa, xb, ya, yb, alpha, beta, typeBoundary, typeElement);
        this.FUNCTION_UEXACT = uex;
        this.FUNCTION_DIV_UEXACT = duex;
        this.FUNCTION_F = funcf;
        this.GCOORD = gcoord;
        this.lambda1 = lambda1;
        this.lambda2 = lambda2;
    }
    // constructor 1D parabolic
    public MainFVM(int nx,int nt,float dt, float xa, float xb,float kappa, float alpha, float beta, String typeBoundary,
            String typeElement,String typeScheme,float lambda1, float lambda2,String uex, String duex, String funcf,double[][]gcoord ){
        super(nx, nt, dt, xa, xb, kappa, alpha, beta, typeBoundary, typeElement, typeScheme);
        this.FUNCTION_UEXACT = uex;
        this.FUNCTION_DIV_UEXACT = duex;
        this.FUNCTION_F = funcf;
        this.GCOORD = gcoord;
        this.lambda1 = lambda1;
        this.lambda2 = lambda2;

    }
    // constructor 1D  elliptic
    public MainFVM(int nx, float xa, float xb,float alpha, float beta, String typeBoundary,String typeElement,
            float lambda1, float lambda2, String uex, String duex, String funcf,double[][]gcoord ){
        super(nx, xa, xb, alpha, beta, typeBoundary, typeElement);
        this.FUNCTION_UEXACT = uex;
        this.FUNCTION_DIV_UEXACT = duex;
        this.FUNCTION_F = funcf;
        this.GCOORD = gcoord;
        this.lambda1 = lambda1;
        this.lambda2 = lambda2;
    }
    //</editor-fold>
    
    public void mainEllipticFVM1D() throws IOException{
        FVM.FVM1D elliptic = new FVM.FVM1D(getNx(),getXa(),getXb(),getAlpha(),getBeta(),getTypeBoundary(),getTypeElement());
        
        double[] gcoord=null;
        if ( GCOORD == null ){
            gcoord = elliptic.createMesh(getXa(), getXb(), getNx() + 1);
        }else{
            for (int i = 0; i < GCOORD.length; i++) 
                gcoord[i] = GCOORD[i][0];
        }
        
        double[]cpoint = elliptic.createControlPoint(gcoord);
        
        double[] u_dis= elliptic.solveElliptic(gcoord,cpoint,lambda1,lambda2,FUNCTION_UEXACT,FUNCTION_DIV_UEXACT,FUNCTION_F) ;
       
        
        //test
        double[] u_exact = new double[getNx()+2];
            for (int i = 0; i < getNx()+2; i++)
                u_exact[i] = elliptic.valueMath(FUNCTION_UEXACT,cpoint[i]);
        
        elliptic.writeFile(u_exact,"u_exact.txt"); 
        elliptic.writeFile(u_dis,"u_dis.txt");
        elliptic.writeFile(gcoord,"gcoord.txt");
        elliptic.writeFile(cpoint,"cpoint.txt");
        elliptic.writeFile(gcoord,"MaxError.txt");
        System.out.println("Max Error = "+elliptic.maxError(u_dis, u_exact));
    }
    
    public void mainParabolicFVM1D() throws IOException{
        FVM.FVM1D parabolic = new FVM.FVM1D(getNx(),getNt(),getDt(),getXa(),getXb(),getKappa(),getAlpha(),getBeta(),getTypeBoundary(),getTypeElement(),getTypeScheme());

        double[] gcoord = null;
        if ( GCOORD == null ){
            gcoord = parabolic.createMesh(getXa(), getXb(), getNx() + 1);
        }else{
            for (int i = 0; i < GCOORD.length; i++) 
                gcoord[i] = GCOORD[i][0];
        }
        
        double[]cpoint = parabolic.createControlPoint(gcoord);
        double[]time   = parabolic.createMesh(0,getNt(),getDt());

        
        double[][] u_dis = parabolic.solveParabolic(gcoord,cpoint,time,lambda1,lambda2,FUNCTION_UEXACT,FUNCTION_DIV_UEXACT,FUNCTION_F);
        

        
        //test
        double[][] u_exact = new double[getNx()+2][getNt()];
        for (int n = 0; n < getNt(); n++) {
            for (int i = 0; i < getNx()+2; i++)
                u_exact[i][n] = parabolic.valueMath(FUNCTION_UEXACT, cpoint[i], time[n]);
        }
        parabolic.writeFile(u_exact,cpoint.length,getNt(),"u_exact.txt");
        parabolic.writeFile(u_dis,cpoint.length,getNt(),"u_dis.txt");
        parabolic.writeFile(gcoord,"gcoord_space.txt");
        parabolic.writeFile(cpoint,"cpoint.txt");
        parabolic.writeFile(time,"gcoord_time.txt");
    }
    
    public void mainEllipticFVM2D(){
    
    }
    
    public void mainParabolicFVM2D(){
    
    }
    
}
