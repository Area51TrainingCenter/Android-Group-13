package pe.area51.fragmentapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.area51.fragmentapp.R;
import pe.area51.fragmentapp.models.Message;

public class ContentFragment extends Fragment {

    public void setMessage(final Message message) {
        ((TextView) getView().findViewById(R.id.fragment_content_textview_content)).setText(message.getContent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

}
