package data_structures;

public enum CellStatus
{
    fixed("F", 4),
    possible("P", 2),
    notpossible("N", 1),
    calculated("C", 3),
    exclude("X", 0);


    private final String symbol;
    private final int weight;

    CellStatus(String f, int weight)
    {
        this.symbol = f;
        this.weight = weight;
    }

    public String symbol()
    {
        return this.symbol;
    }

    public int weight()
    {
        return weight;
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
            case "X":
                return exclude;
            default:
                return notpossible;
        }
    }
}
