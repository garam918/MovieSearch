# 영화 검색 앱 만들기

- Databinding과 ViewModel, Repository를 이용해 MVVM + Repository 패턴으로 설계했습니다.

## 기능 구현
1. 영화 검색 메인화면
- 검색어를 입력하고 검색 버튼을 클릭하면 Retrofit2를 이용해 API를 호출했고, RecyclerView를 이용해 화면에 표시했습니다.
  - 빈 검색어의 경우 검색어를 입력해달라는 Toast 메시지를 출력시켰습니다.
  - 검색된 내용이 없을 경우 검색된 내용이 없다는 Toast 메시지를 출력시켰습니다.
- 영화 이미지와 제목, 출시일, 평점 정보를 보여주도록 했고, 영화 이미지의 경우 coil 라이브러리를 이용했습니다.

- 검색 결과가 2페이지 이상일 경우 페이징 처리는 구현하지 못했습니다.

2. 웹 브라우저 화면
 - 검색한 목록에서 해당 영화를 클릭 시 웹 브라우저로 url을 로드해서 보여주도록 했습니다.

3. 최근 검색 이력 화면
 - 영화를 검색할 때마다 해당 검색어를 데이터베이스에 저장시켰고, 최근 검색 버튼을 클릭하면 Dialog와 RecyclerView를 이용해 최근에 검색했던 검색어를 10개까지 보여주도록 했습니다.
   - 가장 최근에 검색한 검색어를 맨 위에 오게 했습니다.
   - Room을 이용해 앱을 종료하고 다시 실행 했을 때도 저장된 데이터가 사라지지 않게 했습니다.

4. 최근 검색 이력으로 검색한 화면
 - 최근 검색 이력 화면에서 검색어를 클릭하면 dialog를 종료해 메인화면으로 돌아오게 했고, 해당 검색어로 검색한 화면을 보여주도록 했습니다.
 
 ## 사용한 기술 스택 및 라이브러리
- Kotlin 1.7.20
- 네이버 영화 검색 API
- Retrofit2 2.9.0
- Coil 2.2.2
- Room 2.5.0
- RecyclerView 1.2.1
- Coroutine 1.6.4
- Viewmodel 2.5.1
- Livedata 2.5.1
- Databinding
- Flow

## 시연영상
<img src="https://user-images.githubusercontent.com/62510764/215315193-939593af-d1a2-4832-88db-ff2f669c4dab.gif">
