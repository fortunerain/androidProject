package com.example.westinfacemobile.comm;

import com.example.westinfacemobile.R;

import android.app.Application;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class CommApplication extends Application {
	private Fragment parentFragment;
	private FragmentManager fragmentManager;
	
	private Integer[][] activity_list_detail_img = {
			{ R.drawable.activity_detail1_img1,
					R.drawable.activity_detail1_img2,
					R.drawable.activity_detail1_img3 },
			{ R.drawable.activity_detail2_img1,
					R.drawable.activity_detail2_img2,
					R.drawable.activity_detail2_img3 },
			{ R.drawable.activity_detail3_img1,
					R.drawable.activity_detail3_img2,
					R.drawable.activity_detail3_img3 },
			{ R.drawable.activity_detail4_img1,
					R.drawable.activity_detail4_img2,
					R.drawable.activity_detail4_img3 },
			{ R.drawable.activity_detail5_img1,
					R.drawable.activity_detail5_img2,
					R.drawable.activity_detail5_img3 },
			{ R.drawable.activity_detail6_img1,
					R.drawable.activity_detail6_img2,
					R.drawable.activity_detail6_img3 },
			{ R.drawable.activity_detail7_img1,
					R.drawable.activity_detail7_img2,
					R.drawable.activity_detail7_img3 } };

	String[] activity_list_title = { 
			"������ �������",
			"ķ�� ����(CAMVELING = Camping & Traveling)", 
			"������", 
			"������ ��������",
			"���� ���� ����ű", 
			"��Ʈ����", 
			"���鼶 100�� ����" 
	};

	String[] activity_list_desc = {
			"���� �Ƹ����� �ҹ��� �̱�� ������ ���� ���˱��� Ʈ��ŷ�ϸ� �ٴ� ǳ���� ������ ���丮�� ����������. ���ٶ��� ������ ���������� �ٴٸ��� ����濡�� ���� ������ ���������� �������� ��ſ��� �ֽ��ϴ�.",
			"�ؾ������� ���������� �������� ���س��μ� ������ ���� �ĳ��ó�� �а� �������� ǳ���� �����ϸ� Ʈ��ŷ�� �ϰ�, ������ ��� ������ �������� �ٴٰ� ������ ���̴� �ڿ� �ӿ��� ���� ���� ķ���� Ư���� ���� �߾��� ��������.",
			"�ǳ��ε��� ���ܿ� ���� ������ �Ʊ��ڱ��� ���� ������ Ż�ٲ��Ͽ�, ������ ��� ������ ���� ��ǰ��� �ü��� �ӹ��� �ϴ� ��õ ��ȭ ����. ŹƮ�� ������ �λ���, ��������, ������ ��� ������ �� �� �ִ� �ƹ̵� ������. ���� ������ ������ �Ͽ��� ���������� 6.25 ���� �� �ǳ����� �ּ��� ��� �����뱳���� �ٴ� �λ��� �����븦 ��ƺ�����.",
			"�츮���󿡼� ���� ���� ���� �꼺����, ������ ���縸ŭ�̳� ����ִ� �̾߱Ⱑ ���� �����ִ� �����꼺�� ����������. Ǫ�� �� ���� ���� Ʈ��ŷ�ϰ� ���̺�ī�� Ÿ�� �� ������ �����ٺ��� �λ��� ����� ��� �� �ֽ��ϴ�.",
			"���鸸 ���� ��ó�� ��¦�̴� ���ȴ뱳�� ������Ƽ, ȭ���� ���ϼ��� �������� ǳ������ �λ��� ���� ���밡 ���� ������ϴ�.",
			"�ٴٸ� �Ӱ� �����̴� ���� �Ʒ� ���ϸ��� �ϸ� �θ�ƽ�� ������ �׷�������. ���� ������ ���� BBQ, ����, ����, ǳ�� ������ �� ������ �߰�� �����ϸ� �λ� ��ٴ��� �� �ٸ� �ŷ��� ���� �� �ֽ��ϴ�.",
			"���� ���̾����� ���� �����ۿ����� ������ ����Ǿ� ���λ� ����ƾ ����ȣ���� ��å�桯�� �� ��������� �ŴҸ�, ������ ���� ��ħ�� �����غ�����. Ȳ������ �ξ��, �ؿ�� ����, APEC �������� �Ͽ콺 �� ȣ���� �ѷ��� ������ ���� ���丮�� ��ſ��� ���� �帳�ϴ�." };

	String[] activity_list_desc_detail = {
			"�� ��¥: ������, ȭ����, ������, �����, �ݿ���\n�� �Ͻ�: (����)14:00 ~ 17:00\n�� ���: �̱��&������\n* ��Ű, ��������, ��Ÿ�� ����, ����, ��Ƽ���� ����� �賶 �뿩(2�� 1��)\n\n",
			"�� ��¥: ������, �����\n�� �Ͻ�: (����)10:30 ~ 15:00\n�� ���: ���� ���μ� ö�� �̿�ķ��\n* ���� �������� ��˴ϴ�.\n* ����� 3�� ������ ��� �����ϸ�, ���ķδ� ���� �����ᰡ �ΰ��˴ϴ�.\n\n",
			"�� ��¥: ȭ����, �ݿ���, �Ͽ���\n�� �Ͻ�: (����)09:30 ~ 13:00\n�� ���: ��õ��ȭ����, �ƹ̵� ������, �����뱳\n* ��Ű�� �� ���� ���ϻ�, ����, ��Ƽ���� ����� �賶 �뿩 (2�� 1��)\n\n",
			"�� ��¥: ������, �����\n�� �Ͻ�: (����)09:30 ~ 13:00\n�� ���: �����꼺, ���̺�ī\n* ��Ű, ��������, ��Ÿ�� ����, ����, ��Ƽ���� ����� �賶 �뿩 (2�� 1��)\n\n",
			"�� ��¥: ȭ����, ������, �����, �ݿ���, �����, �Ͽ���\n�� �Ͻ�: (����)19:00 ~ 21:00\n�� ���: �̱�� ������, �ݷû� ������, Ȳ�ɻ� ������\n* ��Ŵ�� �Ǵ� �ĳ�� ��������� �����̾� ������ �Ǵ� ������ �ֽ��� �̿��� �� �ִ� ���� ���� ����\n\n",
			"�� ��¥: ȭ����, ������, �����, �ݿ���, �����, �Ͽ���\n�� �Ͻ�: (����)18:00 ~ 20:00\n�� ���: ������ ��Ʈ �����\n* ���� �������� ��˴ϴ�.\n* ����� 3�� ������ ��� �����ϸ�, ���ķδ� ���� �����ᰡ �ΰ��˴ϴ�.\n\n",
			"�� ��¥: ������, ȭ����, ������, �����, �ݿ���, �����, �Ͽ���\n�� �Ͻ�: (����)10:00 ~ 11:00 / (����)14:00 ~ 15:00\n�� ���: ���鼶\n* ���� ������ �����, �Ͽ��Ͽ��� �̿� �����մϴ�.\n* ���� 1�� ����\n\n" };

	String[] group_list_title = { 
			"�ǳ� ���� ü��", 
			"������ ������� & ����ã��", 
			"������ ������ ����",
			"�ؾ緹����",
			"2015 ��� ǳ����Ʈ ����"
	};

	String[] group_list_desc = {
			"�� �Ͻ� : ���� �� ����\n�� ��� : �ǳ� ���� ü����(������)\n\n���κ��� Ȱ �� ��� ���� �����̶�� ���� ���̶�� ������ ���� �������� ���빫�� ������ ü���� ������. ���� �̷� �ñ� ��⸦ �ϸ� ������ ������ ��ſ� �߾��� ����� �帳�ϴ�.",
			"�� �Ͻ� : ���� �� ����\n�� ��� : �̱��, ������\n\nõ���� ����� �ڶ��ϴ� �̱�� ���˱��� ���� Ʈ��ŷ�ϰ�, �踦 Ÿ�� �ź��� �� �������� �ѷ�������. ���ٶ��� ������ �߾��� ���� ã�� ����, ��ü���� Ư���� ������ �����ص帳�ϴ�.",
			"�� �Ͻ� : ���� �� ����\n�� ��� : ��õ��ȭ����, �ƹ̵� ������, �����뱳\n\n�ѱ��� ���丮�� ��õ��ȭ �������� ��ü���� Ư���� ������ ��������. �������� ������, �߾��� ������ ���󰡴� ���� �ٴ� �λ��� �����븦 ���� �� �� �ֽ��ϴ�.",
			"õ���� �ؿ�� �չٴٿ��� ���� �ų��� �ؾ緹����!\n�ÿ��� �ٴ�ٶ��� ������ �� ������ �Բ� Ư���� �߾��� ����� ������.\n\n"
			+ "�� ��¥ : 2015�� 5�� 1��(��) ~ 9�� 30��(��)\n"
			+ "  - 5�� 1��(��) ~ 5�� 31��(��) ��,��,��\n  - 6�� 1��(��) ~ 8�� 31��(��) ����\n"
			+ "  - 5�� 1��(ȭ) ~ 9�� 30��(��) ��,��,��\n"
			+ "�� �ð� : ����2�� ~ ����4��\n�� ��� : 6����/��(����,��� ��� ����)\n"
			+ "�� ��� : The Bay 101\n�� �ο� : �ּ� 4���̻� �̿� ����\n"
			+ "�� �Ʒ��� ��Ű�� �� �ϳ��� �����Ͽ� �̿� ���� �մϴ�.\n"
			+ "  ��Ű��A.\n ��Ʈ��Ʈ + �ٳ�����Ʈ + ����������\n"
			+ "  ��Ű��B.\n ���ǵ庸Ʈ + ���ẸƮ + ����������\n"
			+ "  ��Ű��C.\n ������� + ����������\n",
			"�� ��¥\n"
			+ "  -5�� 03��(��) ~ 5�� 05��(ȭ) | - 5�� 08��(��) ~ 5�� 10��(��)\n"
			+ "  -5�� 15��(��) ~ 5�� 17��(��) | - 5�� 22��(��) ~ 5�� 24��(��)\n"
			+ "�� ��� : �ҿ�ȸ��\n"
			+ "�� �ҿ�ð� : 1�ð�\n"
			+ "�� ������ : ����\n"
	};

	public String[] getActivity_list_title() {
		return activity_list_title;
	}

	public void setActivity_list_title(String[] activity_list_title) {
		this.activity_list_title = activity_list_title;
	}

	public String[] getActivity_list_desc() {
		return activity_list_desc;
	}

	public void setActivity_list_desc(String[] activity_list_desc) {
		this.activity_list_desc = activity_list_desc;
	}

	public String[] getGroup_list_title() {
		return group_list_title;
	}

	public void setGroup_list_title(String[] group_list_title) {
		this.group_list_title = group_list_title;
	}

	public String[] getGroup_list_desc() {
		return group_list_desc;
	}

	public void setGroup_list_desc(String[] group_list_desc) {
		this.group_list_desc = group_list_desc;
	}

	public Fragment getParentFragment() {
		return parentFragment;
	}

	public void setParentFragment(Fragment parentFragment) {
		this.parentFragment = parentFragment;
	}

	/**
	 * onCreate() ��Ƽ��Ƽ, ���ù�, ���񽺰� �����Ǳ��� ���ø����̼��� ���� ���϶� Application onCreate()
	 * �޼��尡 ����� ���ٰ� ���� �ֽ��ϴ�. by. Developer ����Ʈ
	 */
	@Override
	public void onCreate() {
		super.onCreate();
	}

	/**
	 * onConfigurationChanged() ������Ʈ�� ����Ǵ� ���� �ܸ��� ȭ���� �ٲ�� �ý����� ���� �Ѵ�.
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	public String[] getActivity_list_desc_detail() {
		return activity_list_desc_detail;
	}

	public void setActivity_list_desc_detail(String[] activity_list_desc_detail) {
		this.activity_list_desc_detail = activity_list_desc_detail;
	}

	public Integer[][] getActivity_list_detail_img() {
		return activity_list_detail_img;
	}

	public void setActivity_list_detail_img(Integer[][] activity_list_detail_img) {
		this.activity_list_detail_img = activity_list_detail_img;
	}

	public FragmentManager getFragmentManager() {
		return fragmentManager;
	}

	public void setFragmentManager(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}

}
