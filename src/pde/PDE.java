package pde;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.Stack;

public class PDE {
    private int Nx, Ny;
    private int Nt;
    private float kappa,alpha, beta;
    private float dt;    
    private float xa, xb, ya, yb;
    private String typeBoundary;
    private String typeElement;
    private String typeScheme;
    static final double pi = 3.14;
    

    
    //<editor-fold defaultstate="collapsed" desc="Contructor">
    public PDE(){
    
    }
    // contructor  2D  parabolic
    public PDE(int nx, int ny, int nt,float dt, float xa, float xb, float ya, float yb, float kappa, float alpha,float beta, String typeBoundary, String typeElement,String typeScheme){
        this.Nx = nx;
        this.Ny = ny;
        this.Nt = nt;
        this.dt = dt;
        this.xa = xa;
        this.xb = xb;
        this.ya = ya;
        this.yb = yb;
        this.kappa = kappa;
        this.alpha = alpha;
        this.beta = beta;
        this.typeBoundary = typeBoundary;
        this.typeElement = typeElement;
        this.typeScheme = typeScheme;
    }
    // contructor  2D  elliptic
    public PDE(int nx, int ny, float xa, float xb, float ya, float yb, float alpha,float beta, String typeBoundary, String typeElement){
        this.Nx = nx;
        this.Ny = ny;
        this.xa = xa;
        this.xb = xb;
        this.ya = ya;
        this.yb = yb;
        this.alpha = alpha;
        this.beta = beta;
        this.typeBoundary = typeBoundary;
        this.typeElement = typeElement;
    }
    // contructor  1D  parabolic
    public PDE(int nx, int nt,float dt, float xa, float xb,float kappa, float alpha, float beta, String typeBoundary,String typeElement,String typeScheme){
    this.Nx = nx;
    this.Nt = nt;
    this.dt = dt;
    this.xa = xa;
    this.xb = xb;
    this.kappa = kappa;
    this.alpha = alpha;
    this.beta = beta;
    this.typeBoundary = typeBoundary;
    this.typeElement = typeElement;
    this.typeScheme =  typeScheme;
    
    }
    // contructor  1D  elliptic
    public PDE(int nx, float xa, float xb, float alpha, float beta, String typeBoundary,String typeElement){
    this.Nx = nx;
    this.xa = xa;
    this.xb = xb;
    this.alpha = alpha;
    this.beta = beta;
    this.typeBoundary = typeBoundary;
    this.typeElement = typeElement;
    
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="get and set values">

    public int getNt() {
        return Nt;
    }

    public float getDt() {
        return dt;
    }

    public void setNt(int Nt) {
        this.Nt = Nt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }
    
    public void setNx(int nx) {
        this.Nx = nx;
    }

    public void setNy(int ny) {
        this.Ny = ny;
    }

    public float getKappa() {
        return kappa;
    }

    public void setKappa(float kappa) {
        this.kappa = kappa;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setBeta(float beta) {
        this.beta = beta;
    }

    public void setXa(float xa) {
        this.xa = xa;
    }

    public void setXb(float xb) {
        this.xb = xb;
    }

    public void setYa(float ya) {
        this.ya = ya;
    }

    public void setYb(float yb) {
        this.yb = yb;
    }

    public void setTypeScheme(String typeScheme) {
        this.typeScheme = typeScheme;
    }

    public String getTypeScheme() {
        return typeScheme;
    }

    public void setTypeBoundary(String typeBoundary) {
        this.typeBoundary = typeBoundary;
    }

    public void setTypeElement(String typeElement) {
        this.typeElement = typeElement;
    }

    public int getNx() {
        return Nx;
    }

    public int getNy() {
        return Ny;
    }

    public float getAlpha() {
        return alpha;
    }

    public float getBeta() {
        return beta;
    }

    public float getXa() {
        return xa;
    }

    public float getXb() {
        return xb;
    }

    public float getYa() {
        return ya;
    }

    public float getYb() {
        return yb;
    }

    public String getTypeBoundary() {
        return typeBoundary;
    }

    public String getTypeElement() {
        return typeElement;
    }

    public static double getPi() {
        return pi;
    }
  //</editor-fold>  

    //<editor-fold defaultstate="collapsed" desc="number Node and Number Element">
    
    public int nodeX(){
        if (typeElement.compareTo("L2")==0 || typeElement.compareTo("T3")==0 || typeElement.compareTo("Q4")==0)
            return this.Nx + 1;
        else if (typeElement.compareTo("L3")==0 ||typeElement.compareTo("T6")==0 || typeElement.compareTo("Q8")==0)
            return 2*this.Nx + 1;
        else if(typeElement.compareTo("L4")==0 )
            return 3*this.Nx + 1;
        else 
            return 0;
    }   
    public int nodeY(){
        if (typeElement.compareTo("T3")==0 || typeElement.compareTo("Q4")==0)
            return this.Ny + 1;
        else if (typeElement.compareTo("T6")==0 || typeElement.compareTo("Q8")==0)
            return 2*this.Ny + 1;
        else 
            return 0;
    }   
    public int numberNode(){
        if (typeElement.compareTo("Q8")==0)
            return nodeX()*nodeY()- numElement();
        else
            return nodeX()*nodeY();
        }
    public int numElement(){
         if (typeElement.compareTo("T3")==0 || typeElement.compareTo("T6")==0)
            return 2*this.Nx * this.Ny;
         else if (typeElement.compareTo("Q4")==0 || typeElement.compareTo("Q8")==0)
             return this.Nx * this.Ny;
         else
             return 0;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Create mesh">

    public double[] createMesh(float a, float b , int n){
        double[] mesh = new double[n];

        for (int i = 0; i < n; i++){
            mesh[i] = a +  (double)(b-a)*i/(n-1);
        }
        return mesh;
    }
    public double[] createMesh(float a,int n, float dt){
        // create mesh from a to a + dt*n.
        double[] mesh = new double[n];
        mesh[0] = a;
        for (int i = 1; i < n; i++){
            mesh[i] = mesh[i-1] + dt ;
        }
        return mesh;
    }
    public double[][] createMesh2D(){
    	float lx = xb - xa;
	float ly = yb - ya;
        double[][]mesh = null;
        if(typeElement.compareTo("Q8")==0){
            mesh = new double [nodeX()*nodeY()- Nx*Ny][2];
            for (int j = 1; j <=nodeY(); j = j+2) {
                for (int i = 1; i <=nodeX();i++) {
                    int node = (3*Nx+2)*((j-1)/2)+i-1;
                    mesh[node][0] = xa + (i-1)*lx/(nodeX()-1);
                    mesh[node][1] = ya + (j-1)*ly/(nodeY()-1);
                    if (node < (3 * Nx + 2) * Ny ) {
                        mesh[node + nodeX()][0] = xa + (i - 1)*lx / Nx;
                        mesh[node + nodeX()][1] = ya + ((j - 1)/2)*(ly / Ny)+ ly/(2*Ny);
                    }
                }
            }
        }
        else {
            mesh = new double [nodeX()*nodeY()][2];
            for (int j = 1;j<=nodeY();j++){
                for ( int i = 1;i<=nodeX();i++){
                    int node = nodeX()*(j-1)+i-1;
                    mesh[node][0] = xa + (i-1)*lx/(nodeX()-1);
                    mesh[node][1] = ya + (j-1)*ly/(nodeY()-1);
                }
            }
        }
        return mesh;
    }
    
    //</editor-fold>
    
    public double[] ValueBoundary(String uex, String duex, float lampda1, float lampda2) {
        double[] valb = new double [2];
        
        if (getTypeBoundary().compareTo("DIRICHLET") == 0) {
            valb[0] = valueMath(uex, xa);
            valb[1] = valueMath(uex, xb);
        } else if (getTypeBoundary().compareTo("NEUMANN") == 0) {
            valb[0] = valueMath(duex, xa);
            valb[1] = valueMath(duex, xb);
        } else if (getTypeBoundary().compareTo("ROBIN") == 0) {
            valb[0] = valueMath(duex, xa) + lampda1*valueMath(uex, xa);
            valb[1] = valueMath(duex, xb) + lampda2*valueMath(uex, xb);
        }
        return valb;

    }
    public int[] nodeBoundary(double[][] gcoord) {
        int size = 2 * nodeX() + 2 * nodeY() - 4;
        int[] nodebc = new int[size];
        if (getTypeBoundary().compareTo("DIRICHLET") == 0) {
            int node = 0;
            for (int i = 0; i < numberNode(); i++) {
                if (gcoord[i][0] == getXa() || gcoord[i][0] == getXb() || gcoord[i][1] == getYa() || gcoord[i][1] == getYb()) {
                    nodebc[node] = i + 1; // nodes
                    node += 1;
                }
            }
        }
        return nodebc;
    }
    public double[] valueBoundary(double[][] gcoord, String uex, String duex) {
        int size = 2 * nodeX() + 2 * nodeY() - 4;
        double[] valbc = new double[size];
        if (getTypeBoundary().compareTo("DIRICHLET") == 0) {
            int node = 0;
            for (int i = 0; i < numberNode(); i++) {
                if (gcoord[i][0] == getXa() || gcoord[i][0] == getXb() || gcoord[i][1] == getYa() || gcoord[i][1] == getYb()) {
                    valbc[node] = valueMath(uex,gcoord[i][0], gcoord[i][1]); // values at node
                    node += 1;
                }
            }
        }
        return valbc;
    }
        
    //<editor-fold defaultstate="collapsed" desc="Read and Write File">
    
    public void writeFile( double number ,String filename)throws IOException{
        FileOutputStream fo = new FileOutputStream(filename, false);
        try (PrintWriter pw = new PrintWriter(fo)) {
                pw.println(number);
            }
    }

    public void writeFile( double[] array ,String filename)throws IOException{
        FileOutputStream fo = new FileOutputStream(filename, false);
        try (PrintWriter pw = new PrintWriter(fo)) {
            for (int i = 0; i < array.length; i++) {
                pw.println(array[i]);
            }
        }
    }
    public void writeFile(double[][] array,int m,int n, String filename) throws IOException {
        FileOutputStream fo = new FileOutputStream(filename, false);
        try (PrintWriter pw = new PrintWriter(fo)) {
            for (int i = 0; i < m; i++) {
                for(int j = 0;j<n;j++)
                    pw.print(array[i][j] +"      ");
                pw.println();
            }
        }
    }
    
    
    public void readFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        }
    } 
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Error and Norm">
    

    public double maxError (double[]u_dis, double[] u_ex){
        
        double max = Math.abs(u_dis[0] - u_ex[0]);
        for (int i = 1;i<u_dis.length;i++){
            double temp = Math.abs(u_dis[i] - u_ex[i]);
            if(temp > max)
                max = temp;
        }
        return max;
        
    }
    public double norm(double[] u) {
        double norm = 0;
        for (int i = 0; i < u.length; i++) {
            norm += Math.pow(u[i],2);
        }
        norm = Math.sqrt(norm);
        return norm;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="LU factorization">
    

    public void LU_factor(double[][] A,double[][]L, double[][]U) {
        int n = A.length;
        for (int k = 0; k < n; k++) {
            U[k][k] = A[k][k];
            L[k][k] = 1;
            for (int i = k + 1; i < n; i++) {
                L[i][k] = A[i][k] / U[k][k];
                U[k][i] = A[k][i];
            }
            for (int i = k + 1; i < n; i++) {
                for (int j = k + 1; j < n; j++) {
                    A[i][j] -=  L[i][k] * U[k][j];
                }
            }
        }
    }
    
    public double[] Forward(double[][]L, double[] b) {
        int n = L.length;
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            if (L[i][i] != 0) {
                if (i == 0) {
                    x[i] = b[i] / L[i][i];
                } else {
                    x[i] = b[i];
                    for (int j = 0; j < i; j++) {
                        x[i] -= L[i][j] * x[j];
                    }
                    x[i] = x[i] / L[i][i];
                }
            } else {
                return null;    
            }
        }
        return x;

    }
    
    public double[] Backward(double[][] U, double[] b) {
        int n = U.length;
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            if (U[i][i] != 0) {
                if (i == n - 1) {
                    x[i] = b[i] / U[i][i];
                } else {
                    x[i] = b[i];
                    for (int j = i + 1; j < n; j++) {
                        x[i] -= U[i][j] * x[j];
                    }
                    x[i] = x[i] / U[i][i];
                }
            } else {
                return null;
            }
        }
        return x;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Balan">
    

    public int priority(char c){
        //Lay do uu tien cua toan tu
        
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else if (c == '^') return 3;
        else return 0;
    }
    
    public boolean isOperator(char c){  
        // kiem tra xem co phai toan tu
        char[] operator = { '+', '-', '*', '/','^', ')', '(' };
        int temp = 0;
        for (int i = 0; i < operator.length; i++) {
            if (c == operator[i])
                temp +=1;
        }
        return temp != 0;
    }
      
    public String[] processString(String sMath){ 
        // xu ly bieu thuc nhap vao thanh cac phan tu
        String s1 = "", elementMath[];
        sMath = sMath.trim();
        sMath = sMath.replaceAll("\\s+"," "); //  chuan hoa sMath
        for (int i=0; i<sMath.length(); i++){
            char c = sMath.charAt(i);
            if (!isOperator(c)) s1 = s1 + c;
            else s1 = s1 + " " + c + " ";
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+"," "); //    chuan hoa s1
        elementMath = s1.split(" "); //tach s1 thanh cac phan tu
        return elementMath;
    }
    
    public String[] postfix(String[] elementMath){
        String s1 = "", E[];
        Stack <String> S = new Stack <>();

        for (String elementMath1 : elementMath) {
            // duyet cac phan tu
            char c = elementMath1.charAt(0); // c la ky tu dau tien cua moi phan tu
            if (!isOperator(c)) {
                s1 = s1 +" "+ elementMath1; // xuat elem vao s1

            } else {
                // c la toan tu
                if (c == '(') {
                    S.push(elementMath1); // c la "(" -> day phan tu vao Stack
                } else {
                    if (c == ')') {
                        // c la ")"
                        char c1;        //duyet lai cac phan tu trong Stack
                        do{
                            c1 = S.peek().charAt(0);    // c1 la ky tu dau tien cua phan tu
                            if (c1 != '(') s1 = s1 + " " + S.peek();    // trong khi c1 != "("
                            S.pop();
                        }while (c1 != '(');
                    } else {
                        while (!S.isEmpty() && priority(S.peek().charAt(0)) >= priority(c)){
                            // Stack khong rong va trong khi phan tu trong Stack co do uu tien >= phan tu hien tai
                            s1 = s1 + " " + S.peek();   // xuat phan tu trong Stack ra s1
                            S.pop();
                        }
                        S.push(elementMath1); //  dua phan tu hien tai vao Stack
                    }
                }
            }
        }
        while (!S.isEmpty()){   // Neu Stack con phan tu thi day het vao s1
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        s1 = s1.trim();
        E = s1.split(" ");  //  tach s1 thanh cac phan tu

        return E;
    }
    
    public double valueMath(String sMath,double x){
        String[]element = processString(sMath);
        String[] elementMath = postfix(element);

        Stack <String> S = new Stack<>();
        
        for (String elementMath1 : elementMath) {
            char c = elementMath1.charAt(0);
            
            if (!isOperator(c)) {
                S.push(elementMath1); // neu la toan hang, push vao stack
            } else {
                double num = 0,num1,num2;
                
                // neu la toan tu, pop 2 toan hang trong stack ra, roi tinh gia tri cua chung dua vao toan tu nay
                try{
                    String str1 = S.pop(); 
                    if ( str1.compareTo("x")==0) num1 = x;
                    else num1 = Double.parseDouble(str1);
                    String str2 = S.pop();
                    if ( str2.compareTo("x")==0) num2 = x;
                    else num2 = Double.parseDouble(str2);
                } catch(NumberFormatException | EmptyStackException e) { return -1;}
                switch (c) {
                    case '+' : num = num2 + num1; break;
                    case '-' : num = num2 - num1; break;
                    case '*' : num = num2 * num1; break;
                    case '/' : num = num2 / num1; break;
                    case '^' : num = Math.pow(num2, num1); break;
                    default:
                        break;
                }
                S.push(Double.toString(num));   // push ket qua vao lai stack
            }
        }
         return Double.parseDouble(S.pop());   // phan tu tuoi cung trong stack la ket qua.
    }
         
    public double valueMath(String sMath,double x,double y){
        String[]element = processString(sMath);
        String[] elementMath = postfix(element);
        
        Stack <String> S = new Stack<>();
        
        for (String elementMath1 : elementMath) {
            char c = elementMath1.charAt(0);
            
            if (!isOperator(c)) {
                S.push(elementMath1); // neu la toan hang, push vao stack
            } else {
                double num = 0,num1,num2;
                
                // neu la toan tu, pop 2 toan hang trong stack ra, roi tinh gia tri cua chung dua vao toan tu nay
                try{
                    String str1 = S.pop(); 
                    if ( str1.compareTo("x")==0) num1 = x;
                    else if ( str1.compareTo("y")==0 ||str1.compareTo("t")==0) num1 = y;
                    else num1 = Double.parseDouble(str1);
                    String str2 = S.pop();
                    if ( str2.compareTo("x")==0) num2 = x;
                    else if ( str2.compareTo("y")==0|| str2.compareTo("t")==0) num2 = y;
                    else num2 = Double.parseDouble(str2);
                } catch(NumberFormatException | EmptyStackException e) { return -1;}
                switch (c) {
                    case '+' : num = num2 + num1; break;
                    case '-' : num = num2 - num1; break;
                    case '*' : num = num2 * num1; break;
                    case '/' : num = num2 / num1; break;
                    default:
                        break;
                }
                S.push(Double.toString(num));   // push ket qua vao lai stack
            }
        }
         return Double.parseDouble(S.pop());   // phan tu tuoi cung trong stack la ket qua.
    }
    
    
    //</editor-fold>
}
