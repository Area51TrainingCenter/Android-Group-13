package pe.area51.notepad.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import pe.area51.notepad.R;

public class ListFragment extends Fragment {

    private ListView listView;

    //Se puede utilizar este método para tener acceso directo al listview de este fragment.
    public ListView getListView() {
        return listView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.fragment_list_listview_list);
    }
}
