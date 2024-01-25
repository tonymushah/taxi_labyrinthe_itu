package utilities;

public class Int_tab {
    int[] table;
    public void setTable(int[] table) {
        this.table = table;
    }
    public int[] getTable() {
        return table;
    }
    public Int_tab(int[] table){
        this.setTable(table);
    }
    public int length(){
        return this.table.length;
    }
    public int table_n(int n){
        return (this.table[n] % this.length());
    }
    public int[] max_value(){
        /*
            [0] the value
            [1] the index
        */
        int[] returns = new int[2];
        returns[0] = this.table[0];
        returns[1] = 0;
        for(int ip = 0; ip < this.length(); ip++){
            if(returns[0] < this.table_n(ip)){
                returns[0] = this.table_n(ip);
                returns[1] = ip;
            }
        }
        return returns;
    }
    public int[] clone_t(){
        int[] returns = new int[this.table.length];
        for (int i = 0; i < table.length; i++) {
            returns[i] = table[i];
        }
        return returns;
    }
    public double moyenne(){
        int returns = 0;
        for(int imp = 0; imp < this.length(); imp++){
            returns = returns + this.table[imp];
        }
        return (returns / this.length());
    }
    public int[][] max_min(){
        int[][] returns = new int[this.length()][2];
        int[] clone_tab = this.clone_t();
        for(int i = 0; i < returns.length; i++){
            int[] max_value = this.max_value();
            returns[i][0] = max_value[0];
            returns[i][1] = max_value[1];
            table[max_value[1]] = 0;
        }
        this.setTable(clone_tab);
        return returns;
    }
    public int[][] min_max(){
        int[][] returns = new int[this.length()][2];
        int[][] max_tab = this.max_min();
        for (int i = 0; i < max_tab.length; i++) {
            returns[i] = max_tab[(max_tab.length - 1) - i];
        }
        return returns;
    }
    public void show_data(){
        System.out.println("Tab Data : ");
        System.out.println("===========");
        for(int lm = 0; lm < this.length(); lm++){
            System.out.println("Index : " + lm);
            System.out.println("Data : " + this.table[lm]);
            System.out.println("===-_-_-_-_-_-_-_-===");
        }
    }
}
