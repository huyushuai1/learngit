package com.itheima.wave39;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyRing extends View{

	private Paint paint;
	private float cx;
	private float cy;
	private float r;

	public MyRing(Context context, AttributeSet attrs) {
		super(context, attrs);
		
//		init();
		paint = new Paint();
        int i = 0;
        int j = 1;
	}
	
	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true);//打开抗矩齿
		//设置画笔的绘图样式，为绘制线条
		paint.setStyle(Style.STROKE);
		// 设置画笔的线条宽度
		paint.setStrokeWidth(10);
		//设置画笔颜色
		paint.setColor(Color.RED);
		
		//初 始化半径
		r=5;
		
		handler.sendEmptyMessage(99);
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			// 修改圆环的参数
			//修改透明度
			int alpha = paint.getAlpha();
			alpha-=10;
			/*
			 * alpha 的值是在0--255 之间的数字，如果越过这个范围，系统会自动转换
			 *  alpha 值 = -5  与  250 效果是一样的
			 */
			if(alpha<10){
				alpha = 0;
			}
			paint.setAlpha(alpha);
			
			//修改半径
			r+=5;
			
			paint.setStrokeWidth(r/3);
			
			// 刷新页面
			invalidate();
			
			if(alpha >0){
				handler.sendEmptyMessageDelayed(99, 50);
			}
		};
	};
	

	@Override
	/**
	 * 绘制view的内容
	 */
	protected void onDraw(Canvas canvas) {
		
		canvas.drawCircle(cx, cy, r, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			cx = event.getX();
			cy = event.getY();
			
			init();
			
			break;

		default:
			break;
		}
		
		return true;
	}
	
	
	
	
	
	@Override
	/**
	 * 测量大小
	 *  此例，按照系统的默认规则测量大小
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
