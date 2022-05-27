package data_structures;

public enum CellStatus
{
    fixed("F"),
    possible("P"),
    notpossible("N"),
    calculated("C");

    private final String symbol;

    CellStatus(String f)
    {
        this.symbol = f;
    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public static CellStatus getStatusFromString(String item)
    {
        String statusChar = item.substring(0, 1);
        switch (statusChar.toUpperCase())
        {
            case "F":
                return fixed;
            case "P":
                return possible;
            case "C":
                return calculated;
            default:
                return notpossible;
        }
    }
}
