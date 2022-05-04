public class TwoHotPot extends HotPot{
    //套餐内容
    public TwoHotPot() {
        this.meat = "twoMeat";
        this.greens = "twoGreens";
        this.material = "twoMaterial";
    }
    //自定义配菜
    public String prepare() {
        return this.getClass() + "prepare----------";
    }
}
