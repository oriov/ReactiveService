package command;

import observer.Observer;
import observer.Subject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReactiveService {
    private Map<Integer, Subject> subjectsMap = new HashMap<>();
    private Map<Integer, observer.Observer> observersMap = new TreeMap<>();
    private static final String pattern = "\\{(.*?)\\}";
    private static final Pattern r = Pattern.compile(pattern);
    public void buildUserInputAsObserverPattern(String cells){
        String[] cellsList = cells.replace("#", "").split(",");
        createObserversAndSubjectsMaps(cellsList);
        updateSubjects(cellsList);
        notifyAllObservers();
    }

    private void createObserversAndSubjectsMaps(String[] cellsList) {
        for (int i = 0; i < cellsList.length; i++) {
            String s = cellsList[i];
            Subject subject = new Subject(i);
            observer.Observer observer = new observer.Observer(s, observersMap, subject);
            observersMap.put(i, observer);
            subjectsMap.put(i, subject);
        }
    }

    private void updateSubjects(String[] cellsList) {
        for (int i = 0; i < cellsList.length; i++) {
            String s = cellsList[i];
            if (s.contains("{")) {
                Matcher m = r.matcher(s);
                while (m.find()) {
                    int id = Integer.parseInt(m.group(1));
                    Subject subject = subjectsMap.get(id);
                    observer.Observer observer = observersMap.get(i);
                    subject.attach(observer);
                }
            }
        }
    }

    private void notifyAllObservers() {
        for (Subject subject : subjectsMap.values()) {
            subject.notifyAllObservers();
        }
    }

    public List<Double> calculateResults() {
        List<Double> output = new ArrayList<>();
        for (Observer observer : observersMap.values()) {
            output.add(observer.calcResult());
        }
        return output;
    }
}
