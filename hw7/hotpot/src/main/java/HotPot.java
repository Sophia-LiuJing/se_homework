public abstract class HotPot {
    //肉
    protected String meat;
    //菜
    protected String greens;
    //底料
    protected String material;
    //配菜
    public abstract String prepare();
    //套餐
    public void display() {
        System.out.println(this.meat + this.greens + this.material);
    }
}
