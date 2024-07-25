const modi_forms = document.getElementsByClassName("modi_form");

Array.from(modi_forms).forEach((modi_form) => {
	console.dir(modi_form);
	
	// form.elements에서 name 속성 또는 id 속성으로 폼 내부 요소 선택 가능
	const modi_value = modi_form.elements.modi_value;
	const modi_btn = modi_form.elements.modi_btn;
	
	modi_btn.addEventListener("click", (e) => {
		// 해당 이벤트의 기본 동작을 취소하는 메서드
		// e.g. form 내부에서 버튼을 누르면 자동으로 발생하는 submit()을 취소
		e.preventDefault();
		
		// modi_value의 값이 유효한 값이고 동시에 공백이 없을 때만 submit()을 발생
		const mvalue = modi_value.value;
		if (mvalue) {
			if (!mvalue.includes(" ")) {
				modi_form.submit();
				return;
			}
		}
		console.log("공백이 포함되어 있어서 요청을 안 보냈습니다.");
	});
});