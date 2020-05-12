package observer;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Map;

public class Observer {
    private String formula;
    private String result;
    private Map<Integer, Observer> observersMap;
    private Subject subject;

    public Observer(String formula, Map<Integer, Observer> observersMap, Subject subject) {
        this.formula = formula;
        this.result = formula;
        this.observersMap = observersMap;
        this.subject = subject;
    }

    public void update(int id){
        result = result.replace("{" + id + "}", observersMap.get(id).getResult());
    }

    public double calcResult() {
        Expression calc = new ExpressionBuilder(result.replace("=", "")).build();
        result = formula;
        return calc.evaluate();
    }

    public String getResult() {
        return result;
    }

    public Subject getSubject() {
        return subject;
    }
}
