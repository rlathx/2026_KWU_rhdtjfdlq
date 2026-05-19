import './SearchNoneModal.css'
import chatWarnIcon from '../../../../assets/chat/chat-warn.svg'

export default function SearchNoneModal({ onClose }) {
  return (
    <div className='search-none-toast' onClick={onClose}>
      <img className='search-none-toast__icon' src={chatWarnIcon} alt='경고' />
      <p className='search-none-toast__text'>해당 차량을 찾을 수 없어요!</p>
    </div>
  )
}