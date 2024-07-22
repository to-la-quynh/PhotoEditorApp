package com.example.photoeditor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoeditor.filters.FilterAdapter;
import com.example.photoeditor.filters.FilterModel;
import com.example.photoeditor.filters.FilterType;
import com.example.photoeditor.tools.EditingToolAdapter;
import com.example.photoeditor.tools.ToolModel;
import com.example.photoeditor.tools.ToolType;
import com.example.photoeditor.transform.TransformAdapter;
import com.example.photoeditor.transform.TransformModel;
import com.example.photoeditor.transform.TransformType;

import java.util.ArrayList;

public class EditImageActivity extends AppCompatActivity {
    private ImageView imageView;

    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    ArrayList<ToolModel> toolList;
    ArrayList<TransformModel> transformList;
    ArrayList<FilterModel> filterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_image);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();

        String imgUri = intent.getStringExtra("picture");
        Uri uri = Uri.parse(imgUri);
        imageView.setImageURI(uri);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        initTool();
        RecyclerView recyclerView = findViewById(R.id.editingToolRv);
        EditingToolAdapter adapter = new EditingToolAdapter(toolList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(adapter);

        adapter.setToolListener((view, position) -> {
            ToolType type = toolList.get(position).getToolType();
//                Toast.makeText(getApplicationContext(), "type: " + toolList.get(position).getToolType(), Toast.LENGTH_LONG).show();
            switch (type){
                case TRANSFORM:
                    openTransform();
                    break;
                case STICKER:
                    break;
                case FILTER:
                    openFilter();
                    break;
                case DRAW:
                    break;
                case TEXT:
                    break;
                case BRIGHTNESS:
                    break;
            }
            Toast.makeText(getApplicationContext(), "type: " + toolList.get(position).getToolType(), Toast.LENGTH_LONG).show();
        });

    }

//    void initTool(){
//        toolList = new ArrayList<>();
//        toolList.add(new ToolModel(R.drawable.baseline_aspect_ratio_24, ToolType.TRANSFORM));
//        toolList.add(new ToolModel(R.drawable.baseline_auto_fix_high_24, ToolType.FILTER));
//        toolList.add(new ToolModel(R.drawable.baseline_add_reaction_24, ToolType.STICKER));
//        toolList.add(new ToolModel(R.drawable.baseline_border_color_24, ToolType.TEXT));
//        toolList.add(new ToolModel(R.drawable.baseline_brush_24, ToolType.DRAW));
//        toolList.add(new ToolModel(R.drawable.baseline_brightness_6_24, ToolType.BRIGHTNESS));
//    }
//
//
//    void initTransform(){
//        transformList = new ArrayList<>();
//        transformList.add(new TransformModel(R.drawable.baseline_add_reaction_24, TransformType.CROP));
//        transformList.add(new TransformModel(R.drawable.baseline_add_photo_alternate_24, TransformType.ROTATE));
//    }
//
//    void initFilter(){
//        filterList = new ArrayList<>();
//        filterList.add(new FilterModel(FilterType.ORIGINAL.toString(), R.drawable.baseline_add_a_photo_24, FilterType.ORIGINAL));
//        filterList.add(new FilterModel(FilterType.AUTO.toString(), R.drawable.baseline_add_a_photo_24, FilterType.AUTO));
//        filterList.add(new FilterModel(FilterType.CREAM.toString(), R.drawable.baseline_add_a_photo_24, FilterType.CREAM));
//        filterList.add(new FilterModel(FilterType.DEEP.toString(), R.drawable.baseline_add_a_photo_24, FilterType.DEEP));
//        filterList.add(new FilterModel(FilterType.FOREST.toString(), R.drawable.baseline_add_a_photo_24, FilterType.FOREST));
//        filterList.add(new FilterModel(FilterType.CLASSIC.toString(), R.drawable.baseline_add_a_photo_24, FilterType.CLASSIC));
//        filterList.add(new FilterModel(FilterType.COZY.toString(), R.drawable.baseline_add_a_photo_24, FilterType.COZY));
//        filterList.add(new FilterModel(FilterType.GREY_SCALE.toString(), R.drawable.baseline_add_a_photo_24, FilterType.GREY_SCALE));
//    }

    void initTool(){
        toolList = new ArrayList<>();
        toolList.add(new ToolModel(R.drawable.baseline_camera_alt_24, ToolType.TRANSFORM));
        toolList.add(new ToolModel(R.drawable.baseline_camera_alt_24, ToolType.FILTER));
        toolList.add(new ToolModel(R.drawable.baseline_add_photo_alternate_24, ToolType.STICKER));
        toolList.add(new ToolModel(R.drawable.baseline_camera_alt_24, ToolType.TEXT));
        toolList.add(new ToolModel(R.drawable.baseline_camera_alt_24, ToolType.DRAW));
        toolList.add(new ToolModel(R.drawable.baseline_camera_alt_24, ToolType.BRIGHTNESS));
    }


    void initTransform(){
        transformList = new ArrayList<>();
        transformList.add(new TransformModel(R.drawable.baseline_camera_alt_24, TransformType.CROP));
        transformList.add(new TransformModel(R.drawable.baseline_add_photo_alternate_24, TransformType.ROTATE));
    }

    void initFilter(){
        filterList = new ArrayList<>();
        filterList.add(new FilterModel(FilterType.ORIGINAL.toString(), R.drawable.baseline_add_photo_alternate_24, FilterType.ORIGINAL));
        filterList.add(new FilterModel(FilterType.AUTO.toString(), R.drawable.baseline_camera_alt_24, FilterType.AUTO));
        filterList.add(new FilterModel(FilterType.CREAM.toString(), R.drawable.baseline_camera_alt_24, FilterType.CREAM));
        filterList.add(new FilterModel(FilterType.DEEP.toString(), R.drawable.baseline_camera_alt_24, FilterType.DEEP));
        filterList.add(new FilterModel(FilterType.FOREST.toString(), R.drawable.baseline_camera_alt_24, FilterType.FOREST));
        filterList.add(new FilterModel(FilterType.CLASSIC.toString(), R.drawable.baseline_camera_alt_24, FilterType.CLASSIC));
        filterList.add(new FilterModel(FilterType.COZY.toString(), R.drawable.baseline_camera_alt_24, FilterType.COZY));
        filterList.add(new FilterModel(FilterType.GREY_SCALE.toString(), R.drawable.baseline_camera_alt_24, FilterType.GREY_SCALE));
    }

    void openTransform(){
        initTransform();
        RecyclerView recyclerView = findViewById(R.id.detailToolRv);
        TransformAdapter adapter = new TransformAdapter(transformList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        if (recyclerView.getAdapter() != null){
            recyclerView.setAdapter(null);
        }
        recyclerView.setAdapter(adapter);
    }

    void openFilter(){
        initFilter();
        RecyclerView recyclerView = findViewById(R.id.detailToolRv);
        FilterAdapter adapter = new FilterAdapter(filterList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        if (recyclerView.getAdapter() != null){
            recyclerView.setAdapter(null);
        }
            recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }

}