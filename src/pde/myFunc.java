// kha nang ma mot doi tuong co the co duoc (hanh dong chung cua nhieu class)
package pde;

public interface myFunc {
    double[] initvector(int n);
    double[][] initmatrix(int m, int n);
    double[] solveEquation(double[][] A, double[] b);
    
    
}
