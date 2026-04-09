package todo.list.todo.service.nlp;

public class PromptTrainingSample {
    private final String prompt;
    private final String intent;
    private final String priority;
    private final String status;
    private final String expectedTitle;
    private final String expectedDueDate;
    private final String notes;

    public PromptTrainingSample(String prompt, String intent, String priority, String status, String expectedTitle,
            String expectedDueDate, String notes) {
        this.prompt = prompt;
        this.intent = intent;
        this.priority = priority;
        this.status = status;
        this.expectedTitle = expectedTitle;
        this.expectedDueDate = expectedDueDate;
        this.notes = notes;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getIntent() {
        return intent;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getExpectedTitle() {
        return expectedTitle;
    }

    public String getExpectedDueDate() {
        return expectedDueDate;
    }

    public String getNotes() {
        return notes;
    }
}
