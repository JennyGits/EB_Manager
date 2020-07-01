package com.example.my_eb_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Document;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton_add;

    //private WebView webView;
    private Document doc;

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton_add = findViewById(R.id.floatingActionButton_add);
        recyclerView = findViewById(R.id.recycler1);

        int position = getIntent().getIntExtra("position", -1);
        if (position >= 0) {
            itemDelete(position);
        }


        // RecyclerView의 레이아웃 방식을 지정
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        // RecyclerView의 Adapter 세팅
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);

        // ItemTouchHelper 생성
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(new ItemTouchHelperListener() {
            @Override
            public boolean onItemMove(int from_position, int to_position) {
                // 이동할 객체 저장
                //NotiItem notiItem = adapter.items.get(from_position);
                // 이동할 객체 삭제
                //adapter.items.remove(from_position);
                // 이동하고 싶은 position에 추가
                //adapter.items.add(to_position, notiItem);

                // Adapter에 데이터 이동알림
                //adapter.notifyItemMoved(from_position, to_position);
                return false;
                //return true;
            }

            //-- 스와이프 --//
            @Override
            public void onItemSwipe(int position) {
                adapter.items.remove(position);
                adapter.notifyItemRemoved(position);
            }

        }));


        // RecyclerView 에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(recyclerView);

        // 추가 버튼 누름
        floatingActionButton_add.setOnClickListener(v -> {
            Add_NotiItem(); // 아이템 생성
        });


        //// 본선 : webView ////
        /*
        webView = findViewById(R.id.webView);

        // WebView
        // WebView 자바스크립트 활성화
        webView.getSettings().setJavaScriptEnabled(true);
        // 자바스크립트인터페이스 연결
        // 이걸 통해 자바스크립트 내에서 자바함수에 접근할 수 있음.
        webView.addJavascriptInterface(new MyJavascriptInterface(), "Android");
        // 페이지가 모두 로드되었을 때, 작업 정의
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 자바스크립트 인터페이스로 연결되어 있는 getHTML를 실행
                // 자바스크립트 기본 메소드로 html 소스를 통째로 지정해서 인자로 넘김
                view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('body')[0].innerHTML);");
            }
        });
        //지정한 URL을 웹 뷰로 접근하기
        webView.loadUrl("https://www.naver.com");*/

    }

    public void itemDelete(int postion) {
        //adapter.items.remove(postion);
        //adapter.notifyItemRemoved(postion);
    }




    // 아이템 추가 함수
    private void Add_NotiItem() {
        // 객체 생성
        NotiItem notiItem = new NotiItem("누가 내 머리에 똥 쌌어");

        // ListAdapter에 객체 추가
        adapter.addItem(notiItem);
        adapter.notifyDataSetChanged();
    }

    /*private void is_LoginInfo_Exist() {
        // 저장된 정보가 있는지 확인
        if (SaveSharedPreference.getID(MainActivity.this).length() <= 0) {
            // 로그인 액티비티 불러옴
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
        } else {
            // 로그인 하기
            Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }*/


    /*public class MyJavascriptInterface {
        @JavascriptInterface
        public void getHtml(String html) {
            // 위 자바스크립트가 호출되면 여기로 html이 반환됨
            doc = StringToDocument(html);
            // Log.d("MainActivity", "getHtml: " + html);
            // Toast.makeText(MainActivity.this, "asdf", Toast.LENGTH_SHORT).show();
        }
    }
    // String -> Document 변환하는 함수
    public static Document StringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) );
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
