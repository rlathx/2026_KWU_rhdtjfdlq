import Badge from '../../../../components/badge/Badge'
import './ChatListItem.css'

export default function ChatListItem({ plateNumber, lastMessage, isVerified, avatarUrl, onClick }) {
  return (
    // [JS] onClick 연결 — 채팅방으로 이동
    <div className='chat-list-item' onClick={onClick}>
      {/* 아바타 — 사진 없으면 배경색(default), 있으면 이미지 표시 */}
      {avatarUrl ? (
        <img className='chat-list-item__avatar' src={avatarUrl} alt='프로필' />
      ) : (
        <div className='chat-list-item__avatar' />
      )}
      <div className='chat-list-item__body'>
        <div className='chat-list-item__info'>
          <span className='chat-list-item__plate'>{plateNumber}</span>
          <Badge variant={isVerified ? 'verified' : 'unverified'} />
        </div>
        <p className='chat-list-item__last-msg'>{lastMessage}</p>
      </div>
    </div>
  )
}
