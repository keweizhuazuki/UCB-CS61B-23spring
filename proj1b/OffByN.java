public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int X) {
        this.n = X;
    }

    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == n;
    }

}
