/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pde;


/**
 *
 * @author Thuan
 */
public class TEST  extends PDE{
    
    public static void main(String[] args) {
        TEST test = new TEST();
        
        double[][]A = {{89,59,77},{59,107,59},{77,59,89}};
        double[]b  = {1,2,-1};
        double[]x = null;
        x = test.solveEquation(A,b);
        
        for (int i = 0; i < b.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println("---------b----------");
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }  
        System.out.println("---------A----------");
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j <  b.length; j++) {
                System.out.print(A[i][j]+"   ");
            }
            System.out.println();
            
        }     

    }

    private double[] solveEquation(double[][] A, double[] b) {
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

    public double[] initvector(int n) {
        double[] u = new double[n];
        for (int i = 0; i < n; i++) {
            u[i] = 0;
        }
        return u;
    }
    
    public double norm(double[] u) {
        double norm = 0;
        for (int i = 0; i < u.length; i++) {
            norm += Math.pow(u[i],2);
        }
        norm = Math.sqrt(norm);
        return norm;
    }
}

