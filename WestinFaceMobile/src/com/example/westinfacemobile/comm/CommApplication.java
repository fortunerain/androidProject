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
			"오륙도 상륙작전",
			"캠블링 베이(CAMVELING = Camping & Traveling)", 
			"골목대장", 
			"얼쑤좋다 동래구경",
			"달을 향해 하이킥", 
			"요트투어", 
			"동백섬 100배 즐기기" 
	};

	String[] activity_list_desc = {
			"가장 아름답기로 소문난 이기대 절경을 따라 갈맷길을 트레킹하며 바다 풍경이 간직한 스토리를 만나보세요. 봄바람을 맞으며 오륙도까지 다다르는 여행길에는 몸과 마음이 상쾌해지는 ‘쉼’의 즐거움이 있습니다.",
			"해안절벽을 가로지르는 낭만적인 동해남부선 기찻길 따라 파노라마처럼 넓게 펼쳐지는 풍경을 감상하며 트레킹을 하고, 도심을 벗어나 끝없이 펼쳐지는 바다가 내려다 보이는 자연 속에서 즐기는 힐링 캠프로 특별한 봄의 추억을 만들어보세요.",
			"피난민들의 힘겨운 삶의 터전을 아기자기한 예술 마을로 탈바꿈하여, 눈길이 닿는 곳마다 예쁜 작품들로 시선을 머물게 하는 감천 문화 마을. 탁트인 전망의 부산항, 국제시장, 영도를 모두 내려다 볼 수 있는 아미동 전망대. 국내 최초의 유일한 일엽식 도개교이자 6.25 동란 중 피난민의 애수가 담긴 영도대교에서 근대 부산의 발자취를 밟아보세요.",
			"우리나라에서 가장 유서 깊은 산성으로, 오래된 역사만큼이나 재미있는 이야기가 많이 숨어있는 금정산성을 만나보세요. 푸른 산 공기 속을 트레킹하고 케이블카도 타며 산 위에서 내려다보는 부산의 경관을 즐길 수 있습니다.",
			"수백만 개의 별처럼 반짝이는 광안대교와 마린시티, 화려한 은하수가 펼쳐지는 풍경으로 부산의 밤의 정취가 더욱 깊어집니다.",
			"바다를 붉게 물들이는 노을 아래 세일링을 하며 로맨틱한 낭만을 그려보세요. 선상 위에서 즐기는 BBQ, 낚시, 족욕, 풍등 날리기 등 도심의 야경과 조우하며 부산 밤바다의 또 다른 매력을 만날 수 있습니다.",
			"본래 섬이었으나 오랜 퇴적작용으로 육지와 연결되어 ‘부산 웨스틴 조선호텔의 산책길’이 된 동백공원을 거닐며, 상쾌한 봄의 아침을 시작해보세요. 황옥공주 인어상, 해운대 석각, APEC 누리마루 하우스 등 호텔을 둘러싼 곳곳의 숨은 스토리로 즐거움을 더해 드립니다." };

	String[] activity_list_desc_detail = {
			"· 날짜: 월요일, 화요일, 수요일, 목요일, 금요일\n· 일시: (오후)14:00 ~ 17:00\n· 장소: 이기대&오륙도\n* 쿠키, 에너지바, 비타민 워터, 생수, 물티슈가 구비된 배낭 대여(2인 1팩)\n\n",
			"· 날짜: 수요일, 토요일\n· 일시: (오전)10:30 ~ 15:00\n· 장소: 동해 남부선 철길 이오캠프\n* 사전 예약제로 운영됩니다.\n* 출발일 3일 전까지 취소 가능하며, 이후로는 일정 수수료가 부과됩니다.\n\n",
			"· 날짜: 화요일, 금요일, 일요일\n· 일시: (오전)09:30 ~ 13:00\n· 장소: 감천문화마을, 아미동 전망대, 영도대교\n* 베키아 에 누보 단팥빵, 생수, 물티슈가 구비된 배낭 대여 (2인 1팩)\n\n",
			"· 날짜: 월요일, 목요일\n· 일시: (오전)09:30 ~ 13:00\n· 장소: 금정산성, 케이블카\n* 쿠키, 에너지바, 비타민 워터, 생수, 물티슈가 구비된 배낭 대여 (2인 1팩)\n\n",
			"· 날짜: 화요일, 수요일, 목요일, 금요일, 토요일, 일요일\n· 일시: (오후)19:00 ~ 21:00\n· 장소: 이기대 전망대, 금련산 전망대, 황령산 봉수대\n* 오킴스 또는 파노라마 라운지에서 프리미엄 생맥주 또는 생과일 주스를 이용할 수 있는 음료 쿠폰 제공\n\n",
			"· 날짜: 화요일, 수요일, 목요일, 금요일, 토요일, 일요일\n· 일시: (오후)18:00 ~ 20:00\n· 장소: 수영만 요트 경기장\n* 사전 예약제로 운영됩니다.\n* 출발일 3일 전까지 취소 가능하며, 이후로는 일정 수수료가 부과됩니다.\n\n",
			"· 날짜: 월요일, 화요일, 수요일, 목요일, 금요일, 토요일, 일요일\n· 일시: (오전)10:00 ~ 11:00 / (오후)14:00 ~ 15:00\n· 장소: 동백섬\n* 오후 진행은 토요일, 일요일에만 이용 가능합니다.\n* 생수 1병 제공\n\n" };

	String[] group_list_title = { 
			"실내 국궁 체험", 
			"오륙도 상륙작전 & 보물찾기", 
			"골목대장 스탬프 투어",
			"해양레포츠",
			"2015 어린이 풍선아트 교실"
	};

	String[] group_list_desc = {
			"· 일시 : 협의 후 진행\n· 장소 : 실내 국궁 체험장(수영구)\n\n예로부터 활 잘 쏘는 동쪽 민족이라는 뜻의 동이라는 별명을 가진 선조들의 전통무술 국궁을 체험해 보세요. 팀을 이뤄 궁국 경기를 하며 소중한 사람들과 즐거운 추억을 만들어 드립니다.",
			"· 일시 : 협의 후 진행\n· 장소 : 이기대, 오륙도\n\n천혜의 경관을 자랑하는 이기대 갈맷길을 따라 트래킹하고, 배를 타고 신비의 섬 오륙도도 둘러보세요. 봄바람을 느끼며 추억의 보물 찾기 까지, 단체만의 특별한 경험을 선사해드립니다.",
			"· 일시 : 협의 후 진행\n· 장소 : 감천문화마을, 아미동 전망대, 영도대교\n\n한국의 산토리니 감천문화 마을에서 단체만의 특별한 경험을 만들어보세요. 스탬프를 모으며, 추억의 골목길을 따라가다 보면 근대 부산의 발자취를 만나 실 수 있습니다.",
			"천혜의 해운대 앞바다에서 즐기는 신나는 해양레포츠!\n시원한 바닷바람을 맞으며 온 가족이 함께 특별한 추억을 만들어 보세요.\n\n"
			+ "· 날짜 : 2015년 5월 1일(금) ~ 9월 30일(수)\n"
			+ "  - 5월 1일(금) ~ 5월 31일(일) 금,토,일\n  - 6월 1일(월) ~ 8월 31일(월) 매일\n"
			+ "  - 5월 1일(화) ~ 9월 30일(수) 금,토,일\n"
			+ "· 시간 : 오후2시 ~ 오후4시\n· 요금 : 6만원/명(성인,어린이 요금 동일)\n"
			+ "· 장소 : The Bay 101\n· 인원 : 최소 4인이상 이용 가능\n"
			+ "· 아래의 패키지 중 하나를 선택하여 이용 가능 합니다.\n"
			+ "  패키지A.\n 제트보트 + 바나나보트 + 수상자전거\n"
			+ "  패키지B.\n 스피드보트 + 땅콩보트 + 수상자전거\n"
			+ "  패키지C.\n 반잠수정 + 수상자전거\n",
			"· 날짜\n"
			+ "  -5월 03일(일) ~ 5월 05일(화) | - 5월 08일(금) ~ 5월 10일(일)\n"
			+ "  -5월 15일(금) ~ 5월 17일(일) | - 5월 22일(금) ~ 5월 24일(일)\n"
			+ "· 장소 : 소연회장\n"
			+ "· 소요시간 : 1시간\n"
			+ "· 참가비 : 무료\n"
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
	 * onCreate() 액티비티, 리시버, 서비스가 생성되기전 어플리케이션이 시작 중일때 Application onCreate()
	 * 메서드가 만들어 진다고 나와 있습니다. by. Developer 사이트
	 */
	@Override
	public void onCreate() {
		super.onCreate();
	}

	/**
	 * onConfigurationChanged() 컴포넌트가 실행되는 동안 단말의 화면이 바뀌면 시스템이 실행 한다.
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
