import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class AdaptadorCarrera extends BaseAdapter {
    private Context Contexto;
    public ArrayList<Carrera> MiListaCarreras;

    public AdaptadorCarrera(ArrayList<Carrera> miListaCarreras,Context context) {
        this.MiListaCarreras = miListaCarreras;
        this.Contexto=context;
    }
    public int getCount(){
        return MiListaCarreras.size();
    }

    @Override
    public Object getItem(int position) {
        return MiListaCarreras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        return null;
    }
}
