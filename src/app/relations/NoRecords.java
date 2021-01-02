package app.relations;

public class NoRecords implements RelationInterface{
   @Override
   public String format() {
      return "* no records *";
   }
}
