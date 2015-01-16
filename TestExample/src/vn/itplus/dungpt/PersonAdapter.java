package vn.itplus.dungpt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {
	
	Context context;	 
    ArrayList<PersonModel> list;
    LayoutInflater inflater;    

    public PersonAdapter(Context context, ArrayList<PersonModel> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public static class ViewHolder {		 
        public static ImageView img;
        public static TextView txtName;
        public static TextView txtPhone;
        public static ImageView imgNext;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
        ViewHolder viewHolder;
        final PersonModel person = list.get(position);
        if(convertView == null) {
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.item, parent, false);
            viewHolder.txtName = (TextView) v.findViewById(R.id.txtName);
            viewHolder.txtPhone = (TextView) v.findViewById(R.id.txtPhone);
            viewHolder.img = (ImageView) v.findViewById(R.id.imageView1);
            viewHolder.imgNext = (ImageView) v.findViewById(R.id.imageView2);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }
        if(list.size() > 0) {
            viewHolder.txtName.setText(person.getName());
            viewHolder.txtPhone.setText(person.getPhone());
            viewHolder.img.setImageDrawable(person.getImg());
        } else {
            viewHolder.txtName.setText("No Data");
        }
        return v;
	}	
}
