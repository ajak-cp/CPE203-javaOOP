abstract class BinaryExpression
    implements  Expression{

    private final Expression lft;
    private final Expression rht;
    private final String operator;

    protected abstract double applyOperator(Double lft, Double rht);

    public BinaryExpression(Expression lft, Expression rht, String operator) {
        this.lft = lft;
        this.rht = rht;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return lft + " " + operator + " " + rht;
    }

    @Override
    public double evaluate(Bindings bindings) {
        return applyOperator(lft.evaluate(bindings),rht.evaluate(bindings));
    }

}
