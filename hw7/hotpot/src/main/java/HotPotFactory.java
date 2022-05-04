public class HotPotFactory {
    //对象类型
    public static final int ONE_TYPE = 1;
    public static final int TW0_TYPE = 2;
    //对象逻辑
    public HotPot createHotPot(int type) {
        if (type == ONE_TYPE) {
            return new OneHotPot();
        } else if (type == TW0_TYPE) {
            return new TwoHotPot();
        } else {
            return null;
        }
    }
}
