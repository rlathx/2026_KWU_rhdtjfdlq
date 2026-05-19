import './CallRestrictModal.css'

import chatWarnIcon from '../../../../assets/chat/chat-warn.svg'

export default function CallRestrictModal() {
  return (
    <div className='callrestrict-toast'>
      <img className='callrestrict-toast__icon' src={chatWarnIcon} alt='경고' />
      <p className='callrestrict-toast__text'>
        하루 통화 가능 횟수를 초과하여 더 이상 통화가 불가능합니다
      </p>
    </div>
  )
}
