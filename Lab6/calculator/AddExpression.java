class AddExpression extends BinaryExpression
   implements Expression {

   protected AddExpression(Expression lft, Expression rht, String operator) {
      super(lft, rht, operator);
   }

   @Override
   public double applyOperator(Double lft, Double rht) {
      return lft + rht;
   }
}
