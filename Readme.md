## 👀 개요

- 장비에 문제가 생겼을 경우 빠른 초동조치 후 인계하는 것이 목적입니다.
- 영업, 법인, NIT 팀 협업 프로젝트입니다.
- 안드로이드, IOS 모두 제공하는 크로스 플랫폼 앱 서비스 입니다.

---

## ✅ 개발 환경

---

### 📝언어

- HTML5, CSS3, ES6

### 🗄️데이터베이스

- firebase (realtime DB, FCM)

### 📘 프레임 워크 및 라이브러리

- ReactNative, Expo(리액트 라이브러리 빌드 툴)
- Axios (비동기 통신 라이브러리)
- styled-components (css 라이브러리)
- react-native-appearance (라이트모드, 다크모드 같은 설정이 있는지 확인)
- react-navigation (화면전환 지원, native에서 router-dom 같은 라이브러리)
- react-native-dotenv (환경변수 사용을 위한 라이브러리)
- redux(reducer를 묶어주는 combineReducers로 rootReducer 생성)
- react-redux (store 생성을 위한 Prodiver 컴포넌트 제공)
- redux-actions (createAction, handleActions로 액션관리 용이하게 함)

---

## 🗂️기능 목록

- 실시간 앱 푸시 기능(Firebase + Expo push 활용)
- 우선순위 인원 호출 기능(국사, 거리, 직무 등을 고려한 우선순위 존재)
- 로그인 기능(보안 이슈 때문에 서버에 별도 저장하지 않고, 코드에 존재)
- 상황별(출동 전, 후, 조치 완료 등) UI 화면 제공

## 🚨 주의사항(선택)

- branch는 main-develop-issue로 분리해서 사용하고, issue는 한 기능을 구현한 후 바로 삭제합니다.
    - main : promotion 배포
    - develop : 개발서버 배포
    - issue : Add, Feat, Docs, Style, Chore, Fix, Refact ... 로 시작합니다.
