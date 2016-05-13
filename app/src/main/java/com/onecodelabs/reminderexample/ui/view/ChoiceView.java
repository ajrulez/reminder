package com.onecodelabs.reminderexample.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.onecodelabs.reminderexample.R;
import com.onecodelabs.reminderexample.model.Choice;

public class ChoiceView extends BaseFLView {

    private TextView choiceTitle;
    private TextView votes;

    private Choice choice;

    public ChoiceView(Context context, Choice choice) {
        super(context);
        this.choice = choice;
        initialize();
    }

    public ChoiceView(Context context) {
        super(context);
    }

    public ChoiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChoiceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int layout() {
        return R.layout.view_choice;
    }

    @Override
    protected void setUi(View root) {
        choiceTitle = (TextView) root.findViewById(R.id.view_choice_title);
        votes = (TextView) root.findViewById(R.id.view_choice_votes);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void populate() {
        choiceTitle.setText(choice.getChoice());
        votes.setText(choice.getVotes() + "");
    }

    @Override
    protected void setListeners() {

    }
}
