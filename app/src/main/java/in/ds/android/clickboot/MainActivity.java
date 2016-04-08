package in.ds.android.clickboot;

import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.gitonway.lee.niftymodaldialogeffects.lib.*;
import eu.chainfire.libsuperuser.*;
import in.ds.android.clickboot.*;

import in.ds.android.clickboot.R;
import android.app.*;

public class MainActivity extends AppCompatActivity 
{
	public static final String SHUTDOWN_BROADCAST
	= "am broadcast android.intent.action.ACTION_SHUTDOWN";
	public static final int RUNNABLE_DELAY_MS = 500;
	public static final String REBOOT_RECOVERY_CMD = "reboot recovery";
	public static final String SHUTDOWN = "reboot -p";
    public static final String REBOOT_CMD = "reboot";
    public static final String REBOOT_SOFT_REBOOT_CMD = "setprop ctl.restart zygote";
	private static final String REBOOT_BOOTLOADER_CMD = "reboot bootloader";
    private static final String[] REBOOT_SAFE_MODE = new String[]{"setprop persist.sys.safemode 1", REBOOT_SOFT_REBOOT_CMD};
	public static final int BG_PRIO = android.os.Process.THREAD_PRIORITY_BACKGROUND;
	private static final int ROOT_STATUS = R.string.root_status;

	private FloatingActionButton mReboot, mSoftReboot, mRecovery, mShutdown, mServices, mSafeMode, mBootLoader;
	private CoordinatorLayout mCoordinatorLayout;
	private TextView mTv;
	private Effectstype effect;

	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		mTv = (TextView)findViewById(R.id.text);

		mCoordinatorLayout = (CoordinatorLayout)findViewById(R.id.content);
		

		mReboot = (FloatingActionButton)findViewById(R.id.reboot);
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
						.withIcon(getResources().getDrawable(R.drawable.ic_reboot_alpha))
						.isCancelableOnTouchOutside(true)
						.withDuration(RUNNABLE_DELAY_MS)
						.withEffect(effect)
						.withButton1Text("OK")
						//.setCustomView(R.layout.custom_view,v.getContext())
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								new BackgroundThread(REBOOT_CMD).start();
								tampilkanSnackbar(getString(R.string.reboot_message));
								dialogBuilderP1.dismiss();
								showRingProgressDialog();
							}
						})
						.show();
					
					
				}


			});
		mSoftReboot = (FloatingActionButton)findViewById(R.id.soft_reboot);
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
						.withIcon(getResources().getDrawable(R.drawable.soft_reboot))
						.isCancelableOnTouchOutside(true)
						.withDuration(RUNNABLE_DELAY_MS)
						.withEffect(effect)
						.withButton1Text("OK")
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p2) {
								new BackgroundThread(REBOOT_SOFT_REBOOT_CMD).start();
								tampilkanSnackbar(getString(R.string.sf_reboot_message));
								dialogBuilderP2.dismiss();
								showRingProgressDialog();
							}
						})
						.show();
					
				}
			});
		mSafeMode = (FloatingActionButton)findViewById(R.id.safe_mode);
		mSafeMode.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p3)
				{
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
						.withIcon(getResources().getDrawable(R.drawable.soft_reboot))
						.isCancelableOnTouchOutside(true)
						.withDuration(RUNNABLE_DELAY_MS)
						.withEffect(effect)
						.withButton1Text("OK")
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p3) {
								new BackgroundThread(REBOOT_SAFE_MODE).start();
								tampilkanSnackbar(getString(R.string.sf_reboot_message));
								dialogBuilderP3.dismiss();
								showRingProgressDialog();
							}
						})
						.show();
				}
			});
		mBootLoader = (FloatingActionButton)findViewById(R.id.bootloader);
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
						.withIcon(getResources().getDrawable(R.drawable.ic_shutdown))
						.isCancelableOnTouchOutside(true)
						.withDuration(RUNNABLE_DELAY_MS)
						.withEffect(effect)
						.withButton1Text("OK")
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p4) {
								new BackgroundThread(REBOOT_BOOTLOADER_CMD).start();
								tampilkanSnackbar(getString(R.string.recovery_message));
								dialogBuilderP4.dismiss();
								showRingProgressDialog();
							}
						})
						.show();
				}
			});
		mRecovery = (FloatingActionButton)findViewById(R.id.recovery);
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
						.withIcon(getResources().getDrawable(R.drawable.boot_recovery))
						.isCancelableOnTouchOutside(true)
						.withDuration(RUNNABLE_DELAY_MS)
						.withEffect(effect)
						.withButton1Text("OK")
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p5) {
								new BackgroundThread(REBOOT_RECOVERY_CMD).start();
								tampilkanSnackbar(getString(R.string.recovery_message));
								dialogBuilderP5.dismiss();
								showRingProgressDialog();
							}
						})
						.show();
				}


			});

		mShutdown = (FloatingActionButton)findViewById(R.id.shutdown);
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
						.withIcon(getResources().getDrawable(R.drawable.ic_shutdown))
						.isCancelableOnTouchOutside(true)
						.withDuration(RUNNABLE_DELAY_MS)
						.withEffect(effect)
						.withButton1Text("OK")
						.setButton1Click(new View.OnClickListener() {
							@Override
							public void onClick(View p6) {
								new BackgroundThread(SHUTDOWN).start();
								tampilkanSnackbar(getString(R.string.shuut_down_message));
								dialogBuilderP6.dismiss();
								showRingProgressDialog();
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
						.withMessage("App Version : 3.0 \n\nCREDITS :\n\nnaman14 - Material Power Button \nChainfire - SuperUser library \nLiTao : NitfiDialog Efect ")                     //.withMessage(null)  no Msg
						.withMessageColor("#FFFFFFFF")
						.withDialogColor("#1E88E5")
						.withIcon(getResources().getDrawable(R.drawable.ic_launcher))
						.isCancelableOnTouchOutside(true)
						.withDuration(RUNNABLE_DELAY_MS)
						.withEffect(effect)
						.withButton1Text("OK")
						//.setCustomView(R.layout.custom_view,v.getContext())
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
    }
	
	private void showRingProgressDialog(){
        final ProgressDialog ringProgressDialog = ProgressDialog.show(MainActivity.this, "Rebooting", "Please wait ...", true);
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
