import './SafeCallModal.css'

export default function SafeCallModal({ onConfirm, onClose }) {
  return (
    <div className='safecall-modal__overlay'>
      <div className='safecall-modal'>
        {/* 닫기 버튼 — absolute 우상단 */}
        <button className='safecall-modal__close' type='button' onClick={onClose}>
          ✕
        </button>

        {/* 질문 텍스트 중앙 */}
        <div className='safecall-modal__header'>
          <p className='safecall-modal__title'>안심 전화를 시작하시겠습니까?</p>
        </div>

        {/* 가로 구분선 */}
        <div className='safecall-modal__divider-h' />

        {/* 하단 버튼 */}
        <div className='safecall-modal__actions'>
          {/* [JS] 확인 onClick 연결 */}
          <button className='safecall-modal__btn' onClick={onConfirm}>
            확인
          </button>
          <div className='safecall-modal__divider-v' />
          {/* [JS] 취소 onClick 연결 */}
          <button className='safecall-modal__btn' onClick={onClose}>
            취소
          </button>
        </div>
      </div>
    </div>
  )
}
