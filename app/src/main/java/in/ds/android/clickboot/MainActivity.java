package in.ds.android.clickboot;


import android.app.*;
import android.graphics.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.gitonway.lee.niftymodaldialogeffects.lib.*;
import eu.chainfire.libsuperuser.*;
import in.ds.android.clickboot.*;

import in.ds.android.clickboot.R;

public class MainActivity extends AppCompatActivity 
{
	public static final String SHUTDOWN_BROADCAST
	= "am broadcast android.intent.action.ACTION_SHUTDOWN";
	public static final int RUNNABLE_DELAY_MS = 1000;
	public static final int ANIMATION_DURATION = 500;
	public static final String REBOOT_RECOVERY_CMD = "reboot recovery";
	public static final String SHUTDOWN = "reboot -p";
    public static final String REBOOT_CMD = "reboot";
    public static final String REBOOT_SOFT_REBOOT_CMD = "setprop ctl.restart zygote";
	private static final String REBOOT_BOOTLOADER_CMD = "reboot bootloader";
    private static final String[] REBOOT_SAFE_MODE = new String[] {
		"setprop persist.sys.safemode 1",
		REBOOT_SOFT_REBOOT_CMD
	};
	public static final int BG_PRIO = android.os.Process.THREAD_PRIORITY_BACKGROUND;
	private static final int ROOT_STATUS = R.string.root_status;

	private LinearLayout mReboot, mSoftReboot, mRecovery, mShutdown, mSafeMode, mBootLoader;
	private FloatingActionButton mServices;
	private CoordinatorLayout mCoordinatorLayout;
	private TextView mTv;
	private Effectstype effect;
	private TextDrawable drawable1, drawable2, drawable3, drawable4, drawable5, drawable6;

	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mTv = (TextView)findViewById(R.id.text);

		mCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.content);
		

		mReboot = (LinearLayout)findViewById(R.id.reboot);
		mReboot.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					final NiftyDialogBuilder dialogBuilderP1=NiftyDialogBuilder.getInstance(MainActivity.this);
					switch (p1.getId()){
						case R.id.reboot:effect=Effectstype.Shake;break;
					}

					dialogBuilderP1
						.withTitle(getString(R.string.reboot_text))
						.withTitleColor("#FFFFFF")
						.withDividerColor("#11000000")
						.withMessage(getString(R.string.confirm))
						.withMessageColor("#FFFFFFFF")
						.withDialogColor("#1E88E5")
						.isCancelableOnTouchOutside(false)
						.withDuration(ANIMATION_DURATION)
						.withEffect(effect)
						.withButton2Text(getString(android.R.string.cancel))
						.withButton1Text(getString(android.R.string.ok))
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								new BackgroundThread(REBOOT_CMD).start();
								tampilkanSnackbar(getString(R.string.reboot_message));
								dialogBuilderP1.dismiss();
								showRingProgressDialog();
							}
						})
						.setButton2Click(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								// TODO: Implement this method
								dialogBuilderP1.dismiss();
							}

							
						})
						.show();
					
					
				}


			});
		mSoftReboot = (LinearLayout)findViewById(R.id.soft_reboot);
		mSoftReboot.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p2)
				{
					// TODO: Implement this method
					final NiftyDialogBuilder dialogBuilderP2=NiftyDialogBuilder.getInstance(MainActivity.this);
					switch (p2.getId()){
						case R.id.soft_reboot:effect=Effectstype.Shake;break;
					}

					dialogBuilderP2
						.withTitle(getString(R.string.sf_reboot_text))
						.withTitleColor("#FFFFFF")
						.withDividerColor("#11000000")
						.withMessage(getString(R.string.confirm))
						.withMessageColor("#FFFFFFFF")
						.withDialogColor("#1E88E5")
						.isCancelableOnTouchOutside(false)
						.withDuration(ANIMATION_DURATION)
						.withEffect(effect)
						.withButton2Text(getString(android.R.string.cancel))
						.withButton1Text(getString(android.R.string.ok))
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p2) {
								new BackgroundThread(REBOOT_SOFT_REBOOT_CMD).start();
								tampilkanSnackbar(getString(R.string.sf_reboot_message));
								dialogBuilderP2.dismiss();
								showRingProgressDialog();
							}
						})
						.setButton2Click(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								// TODO: Implement this method
								dialogBuilderP2.dismiss();
							}
						})
						.show();
					
				}
			});
		mSafeMode = (LinearLayout)findViewById(R.id.safe_mode);
		mSafeMode.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p3)
				{
					tampilkanSnackbar("Safe Mode are disabled");
					
					/* Safe Mode Are disabled
					// TODO: Implement this method
					final NiftyDialogBuilder dialogBuilderP3=NiftyDialogBuilder.getInstance(MainActivity.this);
					switch (p3.getId()){
						case R.id.safe_mode:effect=Effectstype.Shake;break;
					}

					dialogBuilderP3
						.withTitle(getString(R.string.safe_mode_text))
						.withTitleColor("#FFFFFF")
						.withDividerColor("#11000000")
						.withMessage(getString(R.string.confirm))
						.withMessageColor("#FFFFFFFF")
						.withDialogColor("#1E88E5")
						.isCancelableOnTouchOutside(false)
						.withDuration(ANIMATION_DURATION)
						.withEffect(effect)
						.withButton2Text(getString(android.R.string.cancel))
						.withButton1Text(getString(android.R.string.ok))
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p3) {
								//new BackgroundThread(REBOOT_SAFE_MODE).start();
								tampilkanSnackbar("Safe Mode are disabled");
								dialogBuilderP3.dismiss();
								//showRingProgressDialog();
							}
						})
						.setButton2Click(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								// TODO: Implement this method
								dialogBuilderP3.dismiss();
							}
						})
						.show(); */
				}
			});
		mBootLoader = (LinearLayout)findViewById(R.id.bootloader);
		mBootLoader.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p4)
				{
					// TODO: Implement this method
					final NiftyDialogBuilder dialogBuilderP4=NiftyDialogBuilder.getInstance(MainActivity.this);
					switch (p4.getId()){
						case R.id.bootloader:effect=Effectstype.Shake;break;
					}

					dialogBuilderP4
						.withTitle(getString(R.string.bootloader_text))
						.withTitleColor("#FFFFFF")
						.withDividerColor("#11000000")
						.withMessage(getString(R.string.confirm))
						.withMessageColor("#FFFFFFFF")
						.withDialogColor("#1E88E5")
						.isCancelableOnTouchOutside(false)
						.withDuration(ANIMATION_DURATION)
						.withEffect(effect)
						.withButton2Text(getString(android.R.string.cancel))
						.withButton1Text(getString(android.R.string.ok))
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p4) {
								new BackgroundThread(REBOOT_BOOTLOADER_CMD).start();
								tampilkanSnackbar(getString(R.string.recovery_message));
								dialogBuilderP4.dismiss();
								showRingProgressDialog();
							}
						})
						.setButton2Click(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								// TODO: Implement this method
								dialogBuilderP4.dismiss();
							}
						})
						.show();
				}
			});
		mRecovery = (LinearLayout)findViewById(R.id.recovery);
		mRecovery.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p5)
				{
					final NiftyDialogBuilder dialogBuilderP5=NiftyDialogBuilder.getInstance(MainActivity.this);
					switch (p5.getId()){
						case R.id.recovery:effect=Effectstype.Shake;break;
					}

					dialogBuilderP5
						.withTitle(getString(R.string.recovery_text))
						.withTitleColor("#FFFFFF")
						.withDividerColor("#11000000")
						.withMessage(getString(R.string.confirm))
						.withMessageColor("#FFFFFFFF")
						.withDialogColor("#1E88E5")
						.isCancelableOnTouchOutside(false)
						.withDuration(ANIMATION_DURATION)
						.withEffect(effect)
						.withButton2Text(getString(android.R.string.cancel))
						.withButton1Text(getString(android.R.string.ok))
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p5) {
								new BackgroundThread(REBOOT_RECOVERY_CMD).start();
								tampilkanSnackbar(getString(R.string.recovery_message));
								dialogBuilderP5.dismiss();
								showRingProgressDialog();
							}
						})
						.setButton2Click(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								// TODO: Implement this method
								dialogBuilderP5.dismiss();
							}
						})
						.show();
				}


			});

		mShutdown = (LinearLayout)findViewById(R.id.shutdown);
		mShutdown.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p6)
				{
					// TODO: Implement this method
					final NiftyDialogBuilder dialogBuilderP6=NiftyDialogBuilder.getInstance(MainActivity.this);
					switch (p6.getId()){
						case R.id.shutdown:effect=Effectstype.Shake;break;
					}

					dialogBuilderP6
						.withTitle(getString(R.string.shut_down_text))
						.withTitleColor("#FFFFFF")
						.withDividerColor("#11000000")
						.withMessage(getString(R.string.confirm))
						.withMessageColor("#FFFFFFFF")
						.withDialogColor("#1E88E5")
						.isCancelableOnTouchOutside(false)
						.withDuration(ANIMATION_DURATION)
						.withEffect(effect)
						.withButton2Text(getString(android.R.string.cancel))
						.withButton1Text(getString(android.R.string.ok))
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p6) {
								new BackgroundThread(SHUTDOWN).start();
								tampilkanSnackbar(getString(R.string.shuut_down_message));
								dialogBuilderP6.dismiss();
								showRingProgressDialog();
							}
						})
						.setButton2Click(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								// TODO: Implement this method
								dialogBuilderP6.dismiss();
							}
						})
						.show();
				}


			});
		mServices = (FloatingActionButton)findViewById(R.id.about_app);
		mServices.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p7)
				{
					// TODO: Implement this method
					final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(MainActivity.this);
					dialogBuilder
						.withEffect(effect=Effectstype.RotateBottom)
						.withTitle(getString(R.string.app_about))
						.withTitleColor("#FFFFFF")
						.withDividerColor("#11000000")
						.withMessage("Copyright DSttr™ 2016 \n\n\n\nApp Version : 5.0 \n\nCREDITS :\n\nnaman14 - Material Power Button \nChainfire - SuperUser library \nLiTao - NitfiDialog Efect ")
						.withMessageColor("#FFFFFFFF")
						.withDialogColor("#1E88E5")
						.withIcon(getResources().getDrawable(R.drawable.ic_launcher))
						.isCancelableOnTouchOutside(false)
						.withDuration(ANIMATION_DURATION)
						.withEffect(effect)
						.withButton1Text(getString(android.R.string.ok))
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View v) {

								dialogBuilder.dismiss();
								

							}

						})
						.show();
				}
			});

		new Thread(new Runnable() {
				@Override
				public void run() {
					setThreadPrio(BG_PRIO);
					Snackbar.make(mCoordinatorLayout, "Cheking Root status", Snackbar.LENGTH_INDEFINITE).show();
					if (Shell.SU.available()) {
						new Handler(Looper.getMainLooper()).post(new Runnable() {
								@Override
								public void run() {
									if (mTv != null) {
										mTv.setText(ROOT_STATUS);
										tampilkanSnackbar(getString(R.string.root_status));
										

									}
								}
							});
					}
				}
			}).start();
			
		drawable1 = TextDrawable.builder()
			.buildRound("R", getColor(R.color.colorAccent));
        ((ImageView)findViewById(R.id.ireboot)).setImageDrawable(drawable1);
		drawable2 = TextDrawable.builder()
			.buildRound("S", getColor(R.color.colorAccent));
        ((ImageView)findViewById(R.id.isoft_reboot)).setImageDrawable(drawable2);
		drawable3 = TextDrawable.builder()
			.buildRound("S", getColor(R.color.colorAccent));
        ((ImageView)findViewById(R.id.isafe_reboot)).setImageDrawable(drawable3);
	    drawable4 = TextDrawable.builder()
			.buildRound("R", getColor(R.color.colorAccent));
        ((ImageView)findViewById(R.id.irecover_reboot)).setImageDrawable(drawable4);
		drawable5 = TextDrawable.builder()
			.buildRound("B", getColor(R.color.colorAccent));
        ((ImageView)findViewById(R.id.ibootloader_reboot)).setImageDrawable(drawable5);
		drawable6 = TextDrawable.builder()
			.buildRound("P", getColor(R.color.colorAccent));
        ((ImageView)findViewById(R.id.ishutdown_reboot)).setImageDrawable(drawable6);
    }
	
	private void showRingProgressDialog(){
        final ProgressDialog ringProgressDialog = ProgressDialog.show(MainActivity.this, "Memulai....", "Tunggu beberapa saat.");
        ringProgressDialog.setCancelable(false);
	}

	private static void setThreadPrio(int prio) {
        android.os.Process.setThreadPriority(prio);
    }

	public static class BackgroundThread extends Thread {
        private Object sCmd;

        private BackgroundThread(Object cmd) {
            this.sCmd = cmd;
        }

        @Override
        public void run() {
            super.run();
            setThreadPrio(BG_PRIO);

            if (sCmd == null)
                return;

            /**
             * Sending a system broadcast to notify apps and the system that we're going down
             * so that they write any outstanding data that might need to be flushed
             */
            Shell.SU.run(SHUTDOWN_BROADCAST);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
					@Override
					public void run() {
						if (sCmd instanceof String)
							Shell.SU.run((String) sCmd);
						else if (sCmd instanceof String[])
							Shell.SU.run((String[]) sCmd);
					}
				}, RUNNABLE_DELAY_MS);
        }
    }
	

	private void tampilkanSnackbar(String message) {
		Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
	}

	
}
