class MultiplyExpression extends BinaryExpression
   implements Expression
{
   @Override
   protected double applyOperator(Double lft, Double rht) {
      return lft * rht;
   }

   public MultiplyExpression(Expression lft, Expression rht, String operator)
   {
     super(lft,rht,operator);
   }

}

