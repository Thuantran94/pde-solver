/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pde;

import java.io.IOException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 *
 * @author Thuan
 */
public class FVM extends PDE{
    
    //<editor-fold defaultstate="collapsed" desc=" Constructor ">
    
    public FVM(){
    }
    public FVM(int nx, int ny, int nt, float dt, float xa, float xb, float ya, float yb, float kappa, float alpha, float beta, String typeBoundary, String typeElement, String typeScheme) {
        super(nx, ny, nt, dt, xa, xb, ya, yb, kappa, alpha, beta, typeBoundary, typeElement, typeScheme);
    }
    public FVM(int nx, int ny, float xa, float xb, float ya, float yb, float alpha, float beta, String typeBoundary, String typeElement) {
        super(nx, ny, xa, xb, ya, yb, alpha, beta, typeBoundary, typeElement);
    }
    public FVM(int nx, int nt, float dt, float xa, float xb, float kappa, float alpha, float beta, String typeBoundary, String typeElement, String typeScheme) {
        super(nx, nt, dt, xa, xb, kappa, alpha, beta, typeBoundary, typeElement, typeScheme);
    }
    public FVM(int nx, float xa, float xb, float alpha, float beta, String typeBoundary, String typeElement) {
        super(nx, xa, xb, alpha, beta, typeBoundary, typeElement);
    }
    //</editor-fold>

    class FVM1D extends PDE implements myFunc{
        
    //<editor-fold defaultstate="collapsed" desc=" Constructor ">
        

    // Constructor parabolic 1D
    public FVM1D(int nx, int nt, float dt, float xa, float xb, float kappa, float alpha, float beta, String typeBoundary, String typeElement, String typeScheme) {
        super(nx, nt, dt, xa, xb, kappa, alpha, beta, typeBoundary, typeElement, typeScheme);
    }
    // Constructor elliptic 1D
    public FVM1D(int nx, float xa, float xb, float alpha, float beta, String typeBoundary, String typeElement) {
        super(nx, xa, xb, alpha, beta, typeBoundary, typeElement);
    }
    //</editor-fold>
    
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
    
    //</editor-fold>

    public double[] createControlPoint(double[] gcoord){
        int n = gcoord.length + 1;
        double[] cpoint = new double[n];
        cpoint[0] = gcoord[0];
        cpoint[n-1] = gcoord[n-2];
        for (int i = 1; i < n-1; i++) {
                cpoint[i] = gcoord[i-1] + (gcoord[i]-gcoord[i-1])/2f;
        }
        return cpoint;
    }
    
    public double[][] solveParabolic(double[] gcoord,double[]cpoint, double[]time,float lambda1,float lambda2, String uex, String duex, String funcf) throws IOException{

        double T,D1,D2;
        double[][] u_dis;
        double[][] U = initmatrix(getNx(), getNt()); // discrete solurions not include boundary. 
        for (int i = 0; i < getNx(); i++)
            U[i][0] = valueMath(uex,cpoint[i+1], time[0]); // initial condition.

        float dx = (getXb()-getXa())/getNx();
        if (getTypeScheme().compareTo("EXPLICIT")==0){
            
            //<editor-fold defaultstate="collapsed" desc=" explicit method ">
            // condition of stability.
            if(getKappa()*getDt()/(dx*dx) >= 0.5f){
                int kt = JOptionPane.showConfirmDialog(null, "The scheme is unstable!. Do you want continute ? ", "Waring", YES_NO_OPTION, WARNING_MESSAGE);
                if (kt == 1)
                    return null;
                    
            }
            // solve discrete solution
            for (int n = 0; n < getNt()- 1; n++) {
                for (int i = 0; i < getNx(); i++) {
                     T  = gcoord[i+1] - gcoord[i]; // get volume
                     D1 = cpoint[i+1] - cpoint[i]; // get distance of two control point form 1 to Nx+1
                     D2 = cpoint[i+2] - cpoint[i+1];// get distance of two control point form 2 to Nx+2
                    
                    double alpha = 1/T*(getKappa()/D1 + getAlpha()/2);
                    double beta  = 1/T*(getKappa()/D2 - getAlpha()/2);
                    double gama  = -1/getDt() + alpha + beta + getBeta();

                    if (getTypeBoundary().compareTo("DIRICHLET")==0){
                        if (i == 0)
                            U[i][n+1] = alpha*valueMath(uex,getXa(),time[n]) - gama*U[i][n] + beta*U[i+1][n];
                        if(i == getNx()-1)
                            U[i][n+1] = alpha*U[i-1][n] - gama*U[i][n] + beta*valueMath(uex,getXb(),time[n]);
                        
                    }else if (getTypeBoundary().compareTo("NEUMANN")==0){
                        if (i == 0)
                            U[i][n+1] = -alpha*D1*valueMath(duex,cpoint[i],time[n]) + (alpha- gama)*U[i][n] + beta*U[i+1][n];
                        if(i == getNx()-1)
                            U[i][n+1] = alpha*U[i-1][n] +(beta - gama)*U[i][n] + beta*D2*valueMath(duex,cpoint[i+2],time[n]);
                    }else if (getTypeBoundary().compareTo("ROBIN")==0){
                        if (i == 0){
                            double bval1 = valueMath(duex,cpoint[i],time[n]) + lambda1*valueMath(uex,cpoint[i],time[n]);
                            U[i][n+1] = -alpha*bval1*D1/(1-lambda1*D1) +(alpha/(1-lambda1*D1) - gama)*U[i][n] + beta*U[i+1][n];
                        }
                        if(i == getNx()-1){
                            double bval2 = valueMath(duex,cpoint[i+2],time[n]) + lambda2*valueMath(uex,cpoint[i+2],time[n]);
                            U[i][n+1] = alpha*U[i-1][n] + (beta/(1+lambda2*D2) - gama)*U[i][n] + beta*bval2*D2/(1+lambda2*D2);
                        }
                    }else
                        return null;
                if (i > 0 && i < getNx()-1){
                    U[i][n+1] = alpha*U[i-1][n] - gama*U[i][n] + beta*U[i+1][n];
                }
                
            U[i][n+1] = getDt()*(U[i][n+1] + valueMath(funcf,cpoint[i+1],time[n]));
                }
            }
            //</editor-fold>
            
        }else if (getTypeScheme().compareTo("IMPLICIT")==0){
            
            //<editor-fold defaultstate="collapsed" desc=" implicit method ">
            
            // create matrix A and vector b
            double[][]A = initmatrix(getNx(),getNx());
            double[]b   = initvector(getNx());
            double[]alpha = initvector(getNx());
            double[]beta  = initvector(getNx());
            double[]gama  = initvector(getNx());
            
            for (int i = 0; i < getNx(); i++) {
                T = gcoord[i + 1] - gcoord[i]; // get volume
                D1 = cpoint[i + 1] - cpoint[i]; // get distance of two control point form 1 to Nx+1
                D2 = cpoint[i + 2] - cpoint[i + 1];// get distance of two control point form 2 to Nx+2

                alpha[i] = 1/T*(getKappa()/D1 + getAlpha()/2);
                beta[i]  = 1/T*(getKappa()/D2 - getAlpha()/2);
                gama[i]  = 1/getDt() + alpha[i] + beta[i] + getBeta();
            }
            
            if (getTypeBoundary().compareTo("DIRICHLET")==0){
                for (int i = 0; i < getNx(); i++) { 

                    // create matrix A
                    A[i][i] = gama[i];
                    if(i == 0)
                        A[i][i+1] = -beta[i];
                    else if (i == getNx()-1)
                        A[i][i-1] = -alpha[i];
                    else{
                        A[i][i+1] = -beta[i];
                        A[i][i-1] = -alpha[i];
                    }
                }
                //create vector b.
                for (int n = 1; n < getNt() ; n++) {
                    for (int i = 0; i < getNx(); i++) {
                        b[i] = (1/getDt())*U[i][n-1] + valueMath(funcf,cpoint[i+1],time[n]);
                        if (i == 0) 
                            b[i] += alpha[i]*valueMath(uex, getXa(), time[n]);

                        if (i == getNx() - 1) 
                            b[i] += beta[i]*valueMath(uex, getXb(), time[n]);
                    }
                    double[] temp = solveEquation(A,b);
                    for (int i = 0; i < getNx(); i++)
                        U[i][n] = temp[i];  
                }
                
            }else if (getTypeBoundary().compareTo("NEUMANN")==0){
                for (int i = 0; i < getNx(); i++) { 
                    // create matrix A
                    if(i == 0){
                        A[i][i+1] = -beta[i];
                        A[i][i]     =-alpha[i] + gama[i];
                    }
                    else if (i == getNx()-1){
                        A[i][i-1] = -alpha[i];
                        A[i][i]     =-beta[i] + gama[i];
                    }
                    else{
                        A[i][i+1] = -beta[i];
                        A[i][i-1] = -alpha[i];
                        A[i][i]   = gama[i];
                    }
                }
                //create vector b.
                for (int n = 1; n < getNt() ; n++) {
                    for (int i = 0; i < getNx(); i++) {
                        b[i] = 1/getDt()*U[i][n-1] + valueMath(funcf,cpoint[i+1],time[n]);
                        if (i == 0) 
                            b[i] -= alpha[i]*(cpoint[1] - cpoint[0])*valueMath(duex, cpoint[i], time[n]);

                        if (i == getNx() - 1) 
                            b[i] += beta[i]*(cpoint[i+2] - cpoint[i+1])*valueMath(duex, cpoint[i+2], time[n]);
                    }

                    double[] temp = solveEquation(A,b);
                    for (int i = 0; i < getNx(); i++)
                        U[i][n] = temp[i];
                }
            }else if (getTypeBoundary().compareTo("ROBIN")==0){
                for (int i = 0; i < getNx(); i++) { 
                    // create matrix A
                    if(i == 0){
                        A[i][i+1] = -beta[i];
                        A[i][i]   =-alpha[i]/(1-lambda1*(cpoint[1] - cpoint[0])) + gama[i];
                    }
                    else if (i == getNx()-1){
                        A[i][i-1] = -alpha[i];
                        A[i][i]     =-beta[i]/(1+lambda2*(cpoint[i+2] - cpoint[i+1])) + gama[i];
                    }
                    else{
                        A[i][i+1] = -beta[i];
                        A[i][i-1] = -alpha[i];
                        A[i][i]   = gama[i];
                    }
                }  
                //create vector b.
                for (int n = 1; n < getNt() ; n++) {
                    for (int i = 0; i < getNx(); i++) {
                        b[i] = 1/getDt()*U[i][n-1] + valueMath(funcf,cpoint[i+1],time[n]);
                        if (i == 0){ 
                            double bval1 = valueMath(duex,cpoint[i],time[n]) + lambda1*valueMath(uex,cpoint[i],time[n]) ;
                            b[i] -= alpha[i]*bval1*(cpoint[1] - cpoint[0])/(1-lambda1*(cpoint[1] - cpoint[0]));
                        }

                        if (i == getNx() - 1){
                            double bval2 = valueMath(duex,cpoint[i+2],time[n]) + lambda2*valueMath(uex,cpoint[i+2],time[n]) ;    
                            b[i] += beta[i]*bval2*(cpoint[i+2] - cpoint[i+1])/(1+lambda2*(cpoint[i+2] - cpoint[i+1]));
                        }
                    }
                    double[] temp = solveEquation(A,b);
                    for (int i = 0; i < getNx(); i++)
                        U[i][n] = temp[i];
                }

            }else return null;
         //</editor-fold>   
            
        }else if (getTypeScheme().compareTo("CRANK-NICOLSON")==0){
            
            //<editor-fold defaultstate="collapsed" desc=" crank-nicloson method ">
            
            // create matrices A, B and vector b
            double[][]A = initmatrix(getNx(),getNx());
            double[][]B = initmatrix(getNx(),getNx());
            double[]b   = initvector(getNx());
            double[]alpha = initvector(getNx());
            double[]beta  = initvector(getNx());
            double[]gama  = initvector(getNx());
            for (int i = 0; i < getNx(); i++) {
                 T = gcoord[i + 1] - gcoord[i]; // get volume
                 D1 = cpoint[i + 1] - cpoint[i]; // get distance of two control point form 1 to Nx+1
                 D2 = cpoint[i + 2] - cpoint[i + 1];// get distance of two control point form 2 to Nx+2

                 alpha[i] = 1/T *(getKappa()/D1 + getAlpha()/2);
                 beta[i]  = 1/T*(getKappa()/D2 - getAlpha()/2);
                 gama[i]  = alpha[i] + beta[i] + getBeta();
            }
            if (getTypeBoundary().compareTo("DIRICHLET")==0){
                for (int i = 0; i < getNx(); i++) {
                    // create matrices
                    A[i][i]= -2/getDt() + gama[i];
                    B[i][i]=  2/getDt() + gama[i];
                    if(i == 0){
                        A[i][i+1] = -beta[i];
                        B[i][i+1] = -beta[i];
                    }
                    else if (i == getNx()-1){
                        A[i][i-1] = -alpha[i];
                        B[i][i-1] = -alpha[i];
                    }
                    else{
                        A[i][i+1] = -beta[i];
                        A[i][i-1] = -alpha[i];
                        B[i][i+1] = -beta[i];
                        B[i][i-1] = -alpha[i];
                    }
                }

                //solve discrete solution
                for (int n = 1; n < getNt() ; n++) {
                    for (int i = 0; i < getNx(); i++) {

                        float temp = 0;
                        for (int k = 0; k < getNx(); k++)
                             temp -= A[i][k]*U[k][n-1];
                            b[i] = temp + valueMath(funcf,cpoint[i+1],time[n-1]) + valueMath(funcf,cpoint[i+1],time[n]);

                        if (i == 0) 
                            b[i] += alpha[i]*(valueMath(uex, cpoint[i], time[n-1]) + valueMath(uex, cpoint[i], time[n]));

                        if (i == getNx() - 1) 
                            b[i] += beta[i]*(valueMath(uex, cpoint[i+2], time[n-1]) + valueMath(uex, cpoint[i+2], time[n]));
                    }

                    double[] u1 = solveEquation(B,b);
                    for (int i = 0; i < getNx(); i++)
                        U[i][n] = u1[i];
                }
            }else if (getTypeBoundary().compareTo("NEUMANN")==0){
                for (int i = 0; i < getNx(); i++) {
                    // create matrices
                    if(i == 0){
                        A[i][i]= -2/getDt() + beta[i] + getBeta();
                        B[i][i]=  2/getDt() + beta[i] + getBeta();
                        A[i][i+1] = -beta[i];
                        B[i][i+1] = -beta[i];
                    }
                    else if (i == getNx()-1){
                        A[i][i]= -2/getDt() + alpha[i] + getBeta();
                        B[i][i]=  2/getDt() + alpha[i] + getBeta();
                        A[i][i-1] = -alpha[i];
                        B[i][i-1] = -alpha[i];
                    }
                    else{
                        A[i][i]= -2/getDt() + gama[i];
                        B[i][i]=  2/getDt() + gama[i];
                        A[i][i+1] = -beta[i];
                        A[i][i-1] = -alpha[i];
                        B[i][i+1] = -beta[i];
                        B[i][i-1] = -alpha[i];
                    }
                }
                //solve discrete solution
                for (int n = 1; n < getNt() ; n++) {
                    for (int i = 0; i < getNx(); i++) {

                        float temp = 0;
                        for (int k = 0; k < getNx(); k++)
                             temp -= A[i][k]*U[k][n-1];
                            b[i] = temp + valueMath(funcf,cpoint[i+1],time[n-1]) + valueMath(funcf,cpoint[i+1],time[n]);

                        if (i == 0) 
                            b[i] -= alpha[i]*(cpoint[1]-cpoint[0])*(valueMath(duex, getXa(), time[n-1]) + valueMath(duex, cpoint[i], time[n]));

                        if (i == getNx() - 1) 
                            b[i] += beta[i]*(cpoint[i+2] - cpoint[i+1])*(valueMath(duex, getXb(), time[n-1]) + valueMath(duex, getXb(), time[n]));
                    }

                    double[] u1 = solveEquation(B,b);
                    for (int i = 0; i < getNx(); i++)
                        U[i][n] = u1[i];
                }
            }else if (getTypeBoundary().compareTo("ROBIN")==0){
                for (int i = 0; i < getNx(); i++) {
                    // create matrices
                    if(i == 0){
                        A[i][i]= -2/getDt() + gama[i] - alpha[i]/(1-lambda1*(cpoint[1]-cpoint[0]));
                        B[i][i]=  2/getDt() + gama[i] - alpha[i]/(1-lambda1*(cpoint[1]-cpoint[0]));
                        A[i][i+1] = -beta[i];
                        B[i][i+1] = -beta[i];
                    }
                    else if (i == getNx()-1){
                        A[i][i]= -2/getDt() + gama[i] - beta[i]/(1+lambda2*(cpoint[i+2]-cpoint[i+1]));
                        B[i][i]=  2/getDt() + gama[i] - beta[i]/(1+lambda2*(cpoint[i+2]-cpoint[i+1]));
                        A[i][i-1] = -alpha[i];
                        B[i][i-1] = -alpha[i];
                    }
                    else{
                        A[i][i]= -2/getDt() + gama[i];
                        B[i][i]=  2/getDt() + gama[i];
                        A[i][i+1] = -beta[i];
                        A[i][i-1] = -alpha[i];
                        B[i][i+1] = -beta[i];
                        B[i][i-1] = -alpha[i];
                    }
                }
                //solve discrete solution
                for (int n = 1; n < getNt() ; n++) {
                    for (int i = 0; i < getNx(); i++) {

                        float temp = 0;
                        for (int k = 0; k < getNx(); k++)
                             temp -= A[i][k]*U[k][n-1];
                            b[i] = temp + valueMath(funcf,cpoint[i+1],time[n-1]) + valueMath(funcf,cpoint[i+1],time[n]);

                        if (i == 0){
                            double bval11 = valueMath(duex, getXa(), time[n-1])+ lambda1*valueMath(uex, getXa(), time[n-1]);
                            double bval12 = valueMath(duex, getXa(), time[n])  + lambda1*valueMath(uex, getXa(), time[n]);
                            b[i] -= alpha[i]*(cpoint[1]-cpoint[0])*(bval11+bval12)/(1-lambda1*(cpoint[1]-cpoint[0]));
                        }
                        if (i == getNx() - 1) {
                            double bval21 = valueMath(duex, getXb(), time[n-1])+ lambda2*valueMath(uex, getXb(), time[n-1]);
                            double bval22 = valueMath(duex, getXb(), time[n])  + lambda2*valueMath(uex, getXb(), time[n]);
                            b[i] += beta[i]*(cpoint[i+2]-cpoint[i+1])*(bval21+bval22)/(1+lambda2*(cpoint[i+2]-cpoint[i+1]));
                        }
                    }

                    double[] u1 = solveEquation(B,b);
                    for (int i = 0; i < getNx(); i++)
                        U[i][n] = u1[i];
                }
                
            }else
                return null;
        }
        //</editor-fold>
        
        else{
            return null;
        }
        
        return u_dis = applyBoundary(U,cpoint,time,lambda1,lambda2,uex,duex);
    }
    
    public double[] solveElliptic(double[] gcoord, double[] cpoint, float lampda1, float lampda2, String uex, String duex, String funcf) throws IOException {
        double[][] A = initmatrix(getNx(), getNx());
        double[] b = initvector(getNx());
        double alpha, beta;

        double[] bval = ValueBoundary(uex,duex,lampda1, lampda2);
        if (getTypeBoundary().compareTo("DIRICHLET") == 0) {
            for (int i = 0; i < getNx(); i++) {

                alpha = 1 / (gcoord[i + 1] - gcoord[i]) * (1 / (cpoint[i + 1] - cpoint[i])     + getAlpha() / 2);
                beta  = 1 / (gcoord[i + 1] - gcoord[i]) * (1 / (cpoint[i + 2] - cpoint[i + 1]) - getAlpha() / 2);

                // create matrix A
                A[i][i] = alpha + beta + getBeta();
                if (i == 0) {
                    A[i][i + 1] = -beta;
                } else if (i == getNx() - 1) {
                    A[i][i - 1] = -alpha;
                } else {
                    A[i][i + 1] = -beta;
                    A[i][i - 1] = -alpha;
                }
            }

                // create vector b
                //float sum = 0;
                for (int j = 0; j < getNx(); j++) {
                    b[j] = valueMath(funcf,cpoint[j + 1]);
                    if (j == 0) {
                        double alpha1 = (1/(gcoord[1]-gcoord[0])*(1/(cpoint[1]-cpoint[0])+ getAlpha()/2));
                        b[j] += alpha1*valueMath(uex,cpoint[j]);
                    }
                    if (j == getNx() - 1) {
                        double beta1 = (1/(gcoord[j+1]-gcoord[j])*(1/(cpoint[j+2]-cpoint[j+1])-getAlpha()/2));
                        b[j] += beta1*valueMath(uex,cpoint[j + 2]);
                    }

                }

        } else if (getTypeBoundary().compareTo("NEUMANN") == 0) {
            for (int i = 0; i < getNx(); i++) {

                alpha = 1 /(gcoord[i + 1] - gcoord[i]) * (1 /(cpoint[i + 1] - cpoint[i])     + getAlpha() / 2);
                beta  = 1 /(gcoord[i + 1] - gcoord[i]) * (1 /(cpoint[i + 2] - cpoint[i + 1]) - getAlpha() / 2);

                // create matrix A
                if (i == 0) {
                    A[i][i+1] = -beta;
                    A[i][i]   = beta + getBeta();
                } else if (i == getNx() - 1) {
                    A[i][i-1] = -alpha;
                    A[i][i]  = alpha + getBeta();
                } else {
                    A[i][i+1] = -beta;
                    A[i][i-1] = -alpha;
                    A[i][i]   = alpha + beta + getBeta();
                }
            }
                // create vector b
                for (int j = 0; j < getNx(); j++) {
                    b[j] = valueMath(funcf,cpoint[j + 1]);
                    if (j == 0) {
                        double alpha1 = 1 /(gcoord[1] - gcoord[0]) * (1 /(cpoint[1] - cpoint[0]) + getAlpha() / 2);
                        b[j] -= alpha1 * bval[0] * (cpoint[j + 1] - cpoint[j]);
                    }
                    if (j == getNx() - 1) {
                        double beta1 = 1 /(gcoord[j + 1] - gcoord[j]) * (1 /(cpoint[j + 2] - cpoint[j + 1]) - getAlpha() / 2);
                        b[j] += beta1 * bval[1] * (cpoint[j + 2] - cpoint[j + 1]);
                    }
                }
                // edit b

        }else if (getTypeBoundary().compareTo("ROBIN") == 0) {
            for (int i = 0; i < getNx(); i++) {

                alpha = 1 /(gcoord[i + 1] - gcoord[i]) * (1 /(cpoint[i + 1] - cpoint[i])     + getAlpha() / 2);
                beta  = 1 /(gcoord[i + 1] - gcoord[i]) * (1 /(cpoint[i + 2] - cpoint[i + 1]) - getAlpha() / 2);

                // create matrix A
                if (i == 0) {
                    A[i][i+1] = -beta;
                    A[i][i]   = -alpha/(1-lampda1*(cpoint[i + 1] - cpoint[i])) +alpha+ beta + getBeta();
                } else if (i == getNx() - 1) {
                    A[i][i-1] = -alpha;
                    A[i][i]   = -beta/(1+lampda2*(cpoint[i + 2] - cpoint[i + 1])) +alpha + beta + getBeta();
                } else {
                    A[i][i+1] = -beta;
                    A[i][i-1] = -alpha;
                    A[i][i]   = alpha + beta + getBeta();
                }
            }    
               // create vector b
                for (int j = 0; j < getNx(); j++) {
                    b[j] = valueMath(funcf,cpoint[j + 1]);
                    if (j == 0) {
                        double alpha1 = 1 /(gcoord[1] - gcoord[0]) * (1 /(cpoint[1] - cpoint[0])+ getAlpha() / 2);
                        b[j] -= alpha1* bval[0] * (cpoint[j + 1] - cpoint[j])/(1-lampda1*(cpoint[1] - cpoint[0]));
                    }
                    if (j == getNx() - 1) {
                        double beta1 = 1 /(gcoord[j + 1] - gcoord[j]) * (1 /(cpoint[j + 2] - cpoint[j + 1]) - getAlpha() / 2);
                        b[j] += beta1* bval[1] * (cpoint[j + 2] - cpoint[j + 1])/(1+lampda2*(cpoint[j + 2] - cpoint[j + 1]));
                    }
                }

        }
        double[] U = solveEquation(A, b);
        return applyBoundary(U, cpoint, lampda1, lampda2, bval); // u_dis
        }

    public double[][] applyBoundary(double[][]U,double[]cpoint,double[]time ,float lambda1, float lambda2, String uex, String duex) throws IOException{
      
        double[][] u_dis = new double[getNx()+2][getNt()];
        if(getTypeBoundary().compareTo("DIRICHLET")==0){
            for (int n = 0; n < getNt(); n++) {
                for (int i = 0; i < getNx()+2; i++) {
                    if( i == 0 || i == getNx()+1)
                        u_dis[i][n] = valueMath(uex,cpoint[i],time[n]);
                    else
                        u_dis[i][n] = U[i-1][n];
                }
            }
        }
        else if(getTypeBoundary().compareTo("NEUMANN")==0){
            for (int n = 0; n < getNt(); n++) {
                for (int i = 0; i < getNx()+2; i++) {
                    if( i == 0 )
                        u_dis[i][n] = U[i][n] - (cpoint[1]-cpoint[0])*valueMath(duex,getXa(),time[n]);
                    else if (i == getNx()+1)
                        u_dis[i][n] = U[i-2][n] + (cpoint[i]-cpoint[i-1])*valueMath(duex,getXb(),time[n]);
                    else
                        u_dis[i][n] = U[i-1][n];
                }
            }
        }
        else if(getTypeBoundary().compareTo("ROBIN")==0){
            for (int n = 0; n < getNt(); n++) {
                for (int i = 0; i < getNx()+2; i++) {
                    if( i == 0 ){
                        double bval1 = valueMath(duex,getXa(),time[n]) + lambda1 * valueMath(uex,getXa(),time[n]);
                        u_dis[i][n] = (U[i][n] - bval1*(cpoint[1]-cpoint[0]))/(1-lambda1*(cpoint[1]-cpoint[0]));
                    }
                    else if (i == getNx()+1){
                        double bval2 = valueMath(duex,getXb(),time[n]) + lambda2 * valueMath(uex,getXb(),time[n]);
                        u_dis[i][n] = (U[i-2][n] + bval2*(cpoint[i]-cpoint[i-1]))/(1+lambda2*(cpoint[i]-cpoint[i-1]));
                    }
                    else
                        u_dis[i][n] = U[i-1][n];
                }
            }
        }
        return u_dis;
    }
    
    public double[] applyBoundary(double[] U, double[] cpoint,float lampda1,float lampda2, double[] bval) {
        double[] u_dis = new double[getNx() + 2];
         
        if(getTypeBoundary().compareTo("DIRICHLET")==0){
            u_dis[0]          = bval[0];
            u_dis[getNx() + 1]= bval[1];
//            for (int i = 1; i < getNx() + 1; i++) 
//                u_dis[i] = U[i - 1];           
             System.arraycopy(U,0,u_dis,1,getNx());
             
        }else if(getTypeBoundary().compareTo("NEUMANN")==0){
            u_dis[0]         = U[0] - (cpoint[1] - cpoint[0])*bval[0];
            u_dis[getNx()+1] = U[getNx()-1] + (cpoint[getNx()+1] - cpoint[getNx()])*bval[1];
//            for (int i = 0; i < getNx(); i++) 
//                u_dis[i+1] = U[i];
            System.arraycopy(U,0,u_dis,1,getNx());
            
        }else if(getTypeBoundary().compareTo("ROBIN")==0){
            if (getAlpha() != 0|| getBeta() != 0){
                JOptionPane.showMessageDialog(null,"T.T . Updating...");
                return null;
            }
            
            u_dis[0]         = U[0] - bval[0]*(cpoint[1] - cpoint[0])/(1-lampda1*(cpoint[1] - cpoint[0]));
            u_dis[getNx()+1] = U[getNx()-1] + bval[1]*(cpoint[getNx()+1] - cpoint[getNx()])/(1+lampda2*(cpoint[getNx()+1] - cpoint[getNx()]));
//            for (int i = 0; i < getNx(); i++) 
//                u_dis[i+1] = U[i];
            System.arraycopy(U, 0, u_dis, 1, getNx());
        }else
            return null;
        
        
        return u_dis;
    }
    
    @Override
    public double[] solveEquation(double[][] A, double[] b) {
        // Jacobi method.
        int n = b.length;
        double[] x = initvector(n);
        double[] y = initvector(n);
        double[] z = initvector(n);
        double err = 1;
        while (err > Math.pow(10, -6)) {
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += A[i][j] * x[j];
                    }
                }
                y[i] = (b[i] - sum) / A[i][i];
                x = y;
            }
            for (int j = 0; j < n; j++) {
                double sum = 0;

                for (int i = 0; i < n; i++) {
                    sum += A[j][i] * x[i];
                }
                z[j] = sum - b[j];
            }
            err = norm(z) / norm(b);
        }
        return x;
    }
    
}

    class FVM2D extends PDE implements myFunc{
        // Constructor parabolic 2D
        public FVM2D(int nx, int ny, int nt, float dt, float xa, float xb, float ya, float yb, float kappa, float alpha, float beta, String typeBoundary, String typeElement, String typeScheme) {
            super(nx, ny, nt, dt, xa, xb, ya, yb, kappa, alpha, beta, typeBoundary, typeElement, typeScheme);
        }
        // Constructor elliptic 2D
        public FVM2D(int nx, int ny, float xa, float xb, float ya, float yb, float alpha, float beta, String typeBoundary, String typeElement) {
            super(nx, ny, xa, xb, ya, yb, alpha, beta, typeBoundary, typeElement);
        }
        //<editor-fold defaultstate="collapsed" desc="Innit somthing">   

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
    
    //</editor-fold>
    
    
    @Override
    public double[] solveEquation(double[][] A, double[] b) {
        // Jacobi method.
        int n = b.length;
        double[] x = initvector(n);
        double[] y = initvector(n);
        double[] z = initvector(n);
        double err = 1;
        while (err > Math.pow(10, -6)) {
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += A[i][j] * x[j];
                    }
                }
                y[i] = (b[i] - sum) / A[i][i];
                x = y;
            }
            for (int j = 0; j < n; j++) {
                double sum = 0;

                for (int i = 0; i < n; i++) {
                    sum += A[j][i] * x[i];
                }
                z[j] = sum - b[j];
            }
            err = norm(z) / norm(b);
        }
        return x;
    }
    }
    
}
    

