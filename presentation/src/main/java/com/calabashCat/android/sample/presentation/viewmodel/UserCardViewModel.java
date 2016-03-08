package com.calabashCat.android.sample.presentation.viewmodel;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.calabashCat.android.sample.data.entities.Business;
import com.calabashCat.android.sample.data.entities.SearchResponse;
import com.calabashCat.android.sample.domain.interactor.DefaultSubscriber;
import com.calabashCat.android.sample.domain.interactor.GetUserList;
import com.calabashCat.android.sample.domain.interactor.UseCase;
import com.calabashCat.android.sample.presentation.AndroidApplication;
import com.calabashCat.android.sample.presentation.R;
import com.calabashCat.android.sample.presentation.navigation.ActivityNavigator;
import com.calabashCat.android.sample.presentation.view.activity.BusinessDetailsActivity;
import com.calabashCat.android.sample.presentation.view.adapter.BusinessesAdapter;
import com.calabashCat.android.sample.presentation.view.fragment.BusinessCardFragment;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.card.action.WelcomeButtonAction;
import com.dexafree.materialList.card.provider.ListCardProvider;
import com.dexafree.materialList.view.MaterialListView;
import com.squareup.picasso.RequestCreator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by boliu on 16-03-5.
 */
public class UserCardViewModel extends LoadingViewModel {
	private final static String TAG = UserCardViewModel.class.getSimpleName();

	public final ObservableBoolean showContentList = new ObservableBoolean(false);

	UseCase getUserList = new GetUserList(AndroidApplication.getContext());

	private ViewDataBinding viewDataBinding;

	private MaterialListView mListView;
	private WeakReference<Fragment> mContext;

	public UserCardViewModel(Fragment context) {
		this.mContext = new WeakReference<Fragment>(context);
	}

	@BindView
	@Override
	public void showLoading() {
		super.showLoading();
		showContentList.set(false);
	}

	@BindView
	@Override
	public void showRetry() {
		super.showRetry();
		showContentList.set(false);
	}

	@BindView
	public void showContentList() {
		showLoading.set(false);
		showRetry.set(false);
		showContentList.set(true);
		//usersListAdapter.set(businessesAdapter);
	}

	public void fillCotentList(Collection<Business> collection) {
		mListView.setItemAnimator(new SlideInLeftAnimator());
		mListView.getItemAnimator().setAddDuration(300);
		mListView.getItemAnimator().setRemoveDuration(300);

		fillArray(collection);

	}

	@BindView
	public void showMoreContent() {
		// userAdapter
	}

	@Command
	public void loadUsersCommand() {
		mListView = (MaterialListView) ((BusinessCardFragment) mContext.get()).getView().findViewById(R.id.material_listview);

		if (showLoading.get()) {
			return;
		}
		showLoading();
		getUserList.execute(new DefaultSubscriber<SearchResponse>() {
			@Override
			public void onNext(SearchResponse searchResponse) {

				Log.d("a", "a");
				Collection<Business> collection = searchResponse.getBusinesses();

				showContentList();
				fillCotentList(collection);
			}

			@Override
			public void onCompleted() {
				super.onCompleted();
			}

			@Override
			public void onError(Throwable e) {
				showRetry();
			}

		});
	}

	@Override
	public View.OnClickListener onRetryClick() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadUsersCommand();
			}
		};
	}

	public BusinessesAdapter.OnItemClickListener onUserItemClick() {
		return new BusinessesAdapter.OnItemClickListener() {
			@Override
			public void onUserItemClicked(Business userModel) {
				Intent intent = BusinessDetailsActivity.getCallingIntent(AndroidApplication.getInstance()
						.getCurrentActivity(), userModel.getReview_count());
				ActivityNavigator.navigateTo(BusinessDetailsActivity.class, intent);
			}
		};
	}

	private void fillArray(Collection<Business> collection) {
		List<Card> cards = new ArrayList<>();
		int i = 0;
		for (Business business : collection) {
			cards.add(getCard(business, i++));
		}
		mListView.getAdapter().addAll(cards);
	}

	private Card getCard(Business business, int position) {
		String title = business.getName();
		String description = business.getPhone();

		final Activity context = mContext.get().getActivity();

//		return new Card.Builder(context)
//				.setTag("SMALL_IMAGE_CARD")
//				.setDismissible()
//				.withProvider(new CardProvider())
//				.setLayout(R.layout.material_small_image_card)
//				.setTitle(title)
//				.setDescription(description)
//				.setDrawable(business.getImage_url())
//		        .setBackgroundColor(Color.BLACK)
//				.setDrawableConfiguration(new CardProvider.OnImageConfigListener() {
//					@Override
//					public void onImageConfigure(@NonNull final RequestCreator requestCreator) {
//						requestCreator
//								.resize(350, 350)
//								.centerCrop();
//					}
//				})
//				.endConfig()
//				.build();

		final CardProvider provider = new Card.Builder(context)
				.setTag("BASIC_IMAGE_BUTTON_CARD")
				.setDismissible()
				.withProvider(new CardProvider<>())
				.setLayout(R.layout.material_basic_image_buttons_card_layout)
				.setTitle(title)
				.setTitleGravity(Gravity.END)
				.setDescription(description)
				.setDescriptionGravity(Gravity.END)
				.setDrawable(business.getImage_url())
				.setDrawableConfiguration(new CardProvider.OnImageConfigListener() {
					@Override
					public void onImageConfigure(@NonNull RequestCreator requestCreator) {
//						requestCreator.fit();
					}
				})
				.addAction(R.id.left_text_button, new TextViewAction(context)
						.setText("left")
						.setTextResourceColor(R.color.black_button)
						.setListener(new OnActionClickListener() {
							@Override
							public void onActionClicked(View view, Card card) {
								Toast.makeText(context, "You have pressed the left button", Toast.LENGTH_SHORT).show();

								mListView.getAdapter().addLike(card);
								card.getProvider().setBackgroundColorNotNotify(Color.RED);
								card.dismiss();
							}
						}))
				.addAction(R.id.right_text_button, new TextViewAction(context)
						.setText("right")
						.setTextResourceColor(R.color.orange_button)
						.setListener(new OnActionClickListener() {
							@Override
							public void onActionClicked(View view, Card card) {
								Toast.makeText(context, "You have pressed the right button on card " + card.getProvider().getTitle(), Toast.LENGTH_SHORT).show();
								card.dismiss();
							}
						}));

			provider.setDividerVisible(true);


		return provider.endConfig().build();

//		return new Card.Builder(mContext.get().getActivity())
//				.setTag("BIG_IMAGE_CARD")
//				.withProvider(new CardProvider())
//				.setLayout(R.layout.material_big_image_card_layout)
//				.setTitle(title)
//				.setSubtitle(description)
//				.setSubtitleGravity(Gravity.END)
//				.setDrawable(business.getImage_url())
//				.setDrawableConfiguration(new CardProvider.OnImageConfigListener() {
//					@Override
//					public void onImageConfigure(@NonNull final RequestCreator requestCreator) {
//						requestCreator
//								.resize(200, 200)
//								.centerCrop();
//					}
//				})
//				.endConfig()
//				.build();

//		CardProvider provider = new Card.Builder(mContext.get().getActivity())
//				.setTag("BASIC_IMAGE_BUTTON_CARD")
//				.setDismissible()
//				.withProvider(new CardProvider<>())
//				.setLayout(R.layout.material_basic_image_buttons_card_layout)
//				.setTitle(title)
//				.setTitleGravity(Gravity.END)
//				.setDescription(description)
//				.setDescriptionGravity(Gravity.END)
//				.setDrawable(business.getImage_url())
//				.setDrawableConfiguration(new CardProvider.OnImageConfigListener() {
//					@Override
//					public void onImageConfigure(@NonNull RequestCreator requestCreator) {
//						requestCreator.fit();
//					}
//				})
//				.addAction(R.id.left_text_button, new TextViewAction(mContext.get().getActivity())
//						.setText("left")
//						.setTextResourceColor(R.color.black_button)
//						.setListener(new OnActionClickListener() {
//							@Override
//							public void onActionClicked(View view, Card card) {
//								Toast.makeText(mContext.get().getActivity(), "You have pressed the left button", Toast.LENGTH_SHORT).show();
//								card.getProvider().setTitle("CHANGED ON RUNTIME");
//							}
//						}))
//				.addAction(R.id.right_text_button, new TextViewAction(mContext.get().getActivity())
//						.setText("right")
//						.setTextResourceColor(R.color.orange_button)
//						.setListener(new OnActionClickListener() {
//							@Override
//							public void onActionClicked(View view, Card card) {
//								Toast.makeText(mContext.get().getActivity(), "You have pressed the right button on card " + card.getProvider().getTitle(), Toast.LENGTH_SHORT).show();
//								card.getProvider().setTitle("CHANGED ON RUNTIME");
//							}
//						}));
//
//		return provider.endConfig().build();
	}


}
