package pe.area51.fragmentapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import pe.area51.fragmentapp.R;
import pe.area51.fragmentapp.models.Message;

public class ListFragment extends Fragment {

    private ListFragmentInterface listFragmentInterface;
    private ListView listView;
    final private Message[] messages = new Message[]{
            new Message("Content 1", " Title 1"),
            new Message("Content 2", " Title 2"),
            new Message("Content 3", " Title 3"),
            new Message("Content 4", " Title 4"),
            new Message("Content 5", " Title 5"),
    };

    public void setListFragmentInterface(final ListFragmentInterface listFragmentInterface) {
        this.listFragmentInterface = listFragmentInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.fragment_list_listview_list);
        listView.setAdapter(new MessageListViewAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listFragmentInterface != null) {
                    listFragmentInterface.onMessageSelected((Message) parent.getItemAtPosition(position));
                }
            }
        });
    }

    private class MessageListViewAdapter extends ArrayAdapter<Message> {

        final Fragment fragment;

        public MessageListViewAdapter(final Fragment fragment) {
            //Podemos ponerle 0 al ID del layout, ya que modificaremos el método "getView" y no se utilizará el layout del constructor.
            super(fragment.getActivity(), 0, messages);
            this.fragment = fragment;
        }

        /**
         * Por defecto, el método "getView" de un ArrayAdapter llama al método "toString()" de cada uno de los modelos para mostrarlo en la lista y utiliza como layout para cada elemento de la lista el layout cuyo ID se pasó en el constructor del mismo.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Por razones de rendimiento, deberíamos utilizar aquí el patrón View Holder u otra técnica similar.
            final View listElement = fragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_list_listview_element, null);
            ((TextView) listElement.findViewById(R.id.fragment_list_listview_element_title)).setText(getItem(position).getTitle());
            return listElement;
        }
    }

    public interface ListFragmentInterface {

        public void onMessageSelected(final Message message);

    }
}
