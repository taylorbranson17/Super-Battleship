
public class App {
    public static void main(String[] args) throws Exception {
        OceanGrid oGrid = new OceanGrid();
        TargetGrid tGrid = new TargetGrid();
        System.out.println(oGrid.print() + "\n\n\n" + tGrid.print());
    }
}
