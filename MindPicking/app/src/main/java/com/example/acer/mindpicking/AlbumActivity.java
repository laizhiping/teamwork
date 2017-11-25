package com.example.acer.mindpicking;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;

public class AlbumActivity extends AppCompatActivity {
    private ImageView imageView=null;
    private Bitmap myBitmap;
    private byte[] mContent;
    private String wordResult=new String();
    private String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},140);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},150);
        imageView = (ImageView) findViewById(R.id.imageView);
        final Intent intent=getIntent();
        int data=intent.getIntExtra("extra_data",1);

        if(data==1){
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(getImageByCamera, 1);
        }
        else{
            Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
            getImage.addCategory(Intent.CATEGORY_OPENABLE);
            getImage.setType("image/*");
            startActivityForResult(getImage, 0);

        }
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(myBitmap!=null) {
                    saveBitmap(myBitmap, "image.JPEG");
                    uploadFile();
                    /*while(wordResult.isEmpty()){
                        if(!wordResult.isEmpty() ){
                        break;
                        }
                    }*/

                }
            }
        });

    }
    /*
    @Override
    public void onBackPress(){
        Intent intent=new Intent();
        //this.setResult(backCode,intent);
        this.finish();
    }*/

    @ Override
    protected void onActivityResult ( int requestCode , int resultCode , Intent data )
    {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            finish();
        }
        ContentResolver resolver = getContentResolver();
        /**
         * 因为两种方式都用到了startActivityForResult方法，
         * 这个方法执行完后都会执行onActivityResult方法， 所以为了区别到底选择了那个方式获取图片要进行判断，
         * 这里的requestCode跟startActivityForResult里面第二个参数对应
         */
        if (requestCode == 0)
        {
            try
            {
                // 获得图片的uri
                Uri originalUri = data.getData();
                // 将图片内容解析成字节数组
                mContent = readStream(resolver.openInputStream(Uri.parse(originalUri.toString())));
                // 将字节数组转换为ImageView可调用的Bitmap对象
                myBitmap = getPicFromBytes(mContent, null);
                // //把得到的图片绑定在控件上显示
                imageView.setImageBitmap(myBitmap);

            } catch ( Exception e )
            {
                System.out.println(e.getMessage());
            }
        } else if (requestCode == 1)
        {
            try
            {
                Bundle extras = data.getExtras();
                if(extras==null){
                    finish();
                }
                myBitmap = (Bitmap) extras.get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                mContent = baos.toByteArray();
            } catch ( Exception e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 把得到的图片绑定在控件上显示
            imageView.setImageBitmap(myBitmap);
        }
    }

    public static Bitmap getPicFromBytes ( byte[] bytes , BitmapFactory.Options opts )
    {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    public static byte[] readStream ( InputStream inStream ) throws Exception
    {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1)
        {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    private void saveBitmap(Bitmap bitmap,String bitName)
    {
        File file = new File("/sdcard/DCIM/Camera/"+bitName);
        imagePath="/sdcard/DCIM/Camera/"+bitName;
        if(file.exists()){
            file.delete();
        }
        FileOutputStream out;
        try{
            out = new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.PNG, 90, out))
            {
                out.flush();
                out.close();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void uploadFile()
    {
        //服务器端地址
        String url = "http://111.231.190.23/gjn/UploadFile";
        //手机端要上传的文件，首先要保存你手机上存在该文件
        //String filePath = Environment.getExternalStorageDirectory()
               //+ "/Download/haha.jpg";

        AsyncHttpClient httpClient = new AsyncHttpClient();

        RequestParams param = new RequestParams();
        try
        {
            param.put("file", new File(imagePath));
            param.put("sid",666);
            param.put("wid",777);

            httpClient.post(url, param, new TextHttpResponseHandler()
            {
                @Override
                public void onStart()
                {
                    super.onStart();

                }

                @Override
                public void onSuccess(int i, Header[] headers, String s) {

                    Log.d("hello","Success:"+s);
                    try{
                        JSONObject jsonObject=new JSONObject(s);
                        JSONArray jsonArray=jsonObject.getJSONArray("words_result");
                        wordResult="";
                        for(int j=0;j<jsonArray.length();j++)
                        {
                            wordResult+=jsonArray.getJSONObject(j).getString("words");
                        }
                       // Toast.makeText(AlbumActivity.this,wordResult, Toast.LENGTH_LONG).show();
                        Intent intentEdit=new Intent(AlbumActivity.this,EditSetActivity.class);
                        intentEdit.putExtra("words",wordResult);
                        startActivity(intentEdit);
                    }
                    catch(Throwable t)
                    {
                        Log.d("notice","can't convert into json");
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                    Toast.makeText(AlbumActivity.this, s, Toast.LENGTH_LONG).show();
                    Log.d("notice", "failure>" +s);
                }
            });

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            Toast.makeText(AlbumActivity.this, "上传文件不存在！", Toast.LENGTH_LONG).show();
        }
    }
}
