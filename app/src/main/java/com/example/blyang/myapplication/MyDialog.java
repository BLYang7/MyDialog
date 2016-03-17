package com.example.blyang.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;


public class MyDialog extends Dialog {

	public MyDialog(Context context) {
		super(context);
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context context;
		private String message;   //展示的提示信息
		private String positiveButtonText;  //确定按钮
		private String negativeButtonText;  //取消按钮
		private View contentView;
		private OnClickListener positiveButtonClickListener;  //确定响应监听
		private OnClickListener negativeButtonClickListener;  //取消响应监听


		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */

		public Builder setTitle(String title) {
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * 响应监听器
		 */
		public Builder setPositiveButton(int positiveButtonText,
				OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText,
				OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText,
				OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText,
				OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * 包含相应的layout文件
		 * @return
		 */
		public MyDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// 实例化一个AlertDialog，并且设定这个Dialog的style
			final MyDialog dialog = new MyDialog(context,R.style.Dialog);

			View layout = inflater.inflate(R.layout.dialog, null);
			dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));


			/**
			 * 确定按钮的监听
			 * 如果在实例化这个dialog的时候，确定按钮不为空，这设置它的显示字符串
			 * 如果确定按钮的监听器不为空，则调用响应的控件，给这个控件的监听器赋值
			 * 相反，如果实例化的时候监听器为空，则让这个控件的visibility设置为GONE
			 */
			if (positiveButtonText != null) {
				((TextView) layout.findViewById(R.id.positiveButton))
						.setText(positiveButtonText);

				if (positiveButtonClickListener != null) {
					((TextView) layout.findViewById(R.id.positiveButton))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.positiveButton).setVisibility(
						View.GONE);
			}

			/**
			 * 取消按钮的监听
			 * 参考确认按钮的说明
			 */
			if (negativeButtonText != null) {
				((TextView) layout.findViewById(R.id.negativeButton))
						.setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					((TextView) layout.findViewById(R.id.negativeButton))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.negativeButton).setVisibility(
						View.GONE);
			}

			/**
			 * 设置展示信息
			 */
			if (message != null) {
				((TextView) layout.findViewById(R.id.message)).setText(message);
			}


			dialog.setContentView(layout);

			//创建完成之后，返回这个dialog
			return dialog;
		}

	}
}
