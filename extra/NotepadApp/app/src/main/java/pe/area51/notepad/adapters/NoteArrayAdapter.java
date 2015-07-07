package pe.area51.notepad.adapters;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pe.area51.notepad.R;
import pe.area51.notepad.models.Note;

public class NoteArrayAdapter extends ArrayAdapter<Note> {

    final Fragment fragment;

    public NoteArrayAdapter(final Fragment fragment, final List<Note> notes) {
        //Podemos ponerle 0 al ID del layout, ya que modificaremos el método "getView" y no se utilizará el layout del constructor.
        super(fragment.getActivity(), 0, notes);
        this.fragment = fragment;
    }

    /**
     * Por defecto, el método "getView" de un ArrayAdapter llama al método "toString()" de cada uno de los modelos para mostrarlo en la lista y utiliza como layout para cada elemento de la lista el layout cuyo ID se pasó en el constructor del mismo.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Por razones de rendimiento, deberíamos utilizar aquí el patrón View Holder u otra técnica similar.
        final View listElement = fragment.getActivity().getLayoutInflater().inflate(R.layout.note_listview_element, null);
        ((TextView) listElement.findViewById(R.id.note_listview_element_title)).setText(getItem(position).getTitle());
        ((TextView) listElement.findViewById(R.id.note_listview_element_creation_time)).setText(String.valueOf(getItem(position).getCreationTime()));
        return listElement;
    }

}
