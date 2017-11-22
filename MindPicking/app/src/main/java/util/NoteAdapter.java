package util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.acer.mindpicking.Folder;
import com.example.acer.mindpicking.Note;
import com.example.acer.mindpicking.R;

import java.util.ArrayList;
import java.util.List;

//每个文件夹下单个图片的适配器 lzp 1109
public class NoteAdapter extends BaseAdapter {
	private Context context;
	private List<Note> notesArrayList = new ArrayList<Note>();

	public NoteAdapter(Context Context, List<Note> notesArrayList) {
		this.context = Context;
		this.notesArrayList = notesArrayList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return notesArrayList.size();
	}

	@Override
	public Object getItem(int i) {
		// TODO Auto-generated method stub
		return notesArrayList.get(i);
	}

	@Override
	public long getItemId(int i) {
		// TODO Auto-generated method stub
		return i;
	}
	@Override
	public View getView(int position, View convertview, ViewGroup viewgroup) {
		//View myView = LayoutInflater.from(context).inflate(R.layout.gallery_item, null);
		//ImageView imageView = (ImageView) myView.findViewById(R.id.image);
		ImageView imageView=new ImageView(context);
		Bitmap bmp = BitmapFactory.decodeFile("/storage/emulated/0/MindPicking/"+notesArrayList.get(position).getImage().toString()+".jpeg");
		imageView.setImageBitmap(bmp);
		return imageView;
	}

}