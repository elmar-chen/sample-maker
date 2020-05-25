//package elmar.test.sample.maker.parser;
//
//import elmar.test.sample.maker.ParseContext;
//import elmar.test.sample.maker.Statement;
//
//public abstract class ParserBuilder implements Parser{
//    public abstract Parser build();
//
//    @Override
//    public boolean parse(ParseContext context) {
//        build().parse(context);
//        return false;
//    }
//    
//    public void add(Class<?> clazz) {
//        
//    }
//    public void list(Class<?> itemClass, String pad) {
//        
//    }
//    public static void main(String[] args) {
//        GrammarNode<Statement> stat = new GrammarNode<>();
//        stat.addTrans("endl");
//        stat.addTrans("end");
//        
//        
//    }
//
//    private static ParserBuilder createFor(Class<Statement[]> class1) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//}
