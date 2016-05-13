package com.onecodelabs.reminderexample.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onecodelabs.reminderexample.R;
import com.onecodelabs.reminderexample.model.Choice;
import com.onecodelabs.reminderexample.model.Question;
import com.onecodelabs.reminderexample.ui.view.ChoiceView;

public class QuestionAdapter extends UtilAdapter<Question, QuestionAdapter.QuestionViewHolder> {

    public QuestionAdapter(Context context) {
        super(context);
    }

    @Override
    protected int layout() {
        return R.layout.adapter_question;
    }

    @Override
    protected QuestionViewHolder viewHolder(View root) {
        return new QuestionViewHolder(root);
    }

    @Override
    protected void populate(Question question, QuestionViewHolder viewHolder) {
        viewHolder.title.setText(question.getQuestion());
        LinearLayout choiceContainer = viewHolder.choiceContainer;
        choiceContainer.removeAllViews();
        for (Choice choice: question.getChoices()) {
            choiceContainer.addView(new ChoiceView(mContext, choice));
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class QuestionViewHolder extends BaseViewHolder {

        TextView title;
        LinearLayout choiceContainer;

        public QuestionViewHolder(View root) {
            super(root);
            title = (TextView) root.findViewById(R.id.adapter_question_title);
            choiceContainer = (LinearLayout) root.findViewById(R.id.adapter_question_container);
        }
    }
}
