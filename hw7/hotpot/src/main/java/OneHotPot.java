public class OneHotPot extends HotPot {
    //套餐内容
    public OneHotPot() {
        this.meat = "oneMeat";
        this.greens = "oneGreens";
        this.material = "oneMaterial";
    }
    //自定义配菜
    public String prepare() {
        return this.getClass() + "prepare----------";
    }
}
