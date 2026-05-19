import Badge from '../../../../components/badge/Badge'
import './VehicleCard.css'

export function VehicleCard({ plateNumber, type, note, isVerified, imageUrl, isEditing, onClick }) {
  return (
    <div
      className={`vehicle-card${isEditing ? ' vehicle-card--editing' : ''}`}
      onClick={isEditing ? onClick : undefined}
    >
      {/* 이미지 영역 — 사진 없으면 배경색(default), 있으면 이미지 표시 */}
      <div className='vehicle-card__image'>
        {imageUrl && <img className='vehicle-card__img' src={imageUrl} alt='차량 사진' />}
        {isVerified && (
          <div className='vehicle-card__badge'>
            <Badge variant='verified' />
          </div>
        )}
      </div>

      <div className='vehicle-card__body'>
        <p className='vehicle-card__plate'>{plateNumber}</p>
        <div className='vehicle-card__divider' />
        <div className='vehicle-card__info'>
          <div className='vehicle-card__info-row'>
            <span className='vehicle-card__info-label'>차종</span>
            <span className='vehicle-card__info-value'>{type}</span>
          </div>
          {note && (
            <div className='vehicle-card__info-row'>
              <span className='vehicle-card__info-label'>특이사항</span>
              <span className='vehicle-card__info-value'>{note}</span>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}

export function VehicleAddCard({ onClick }) {
  return (
    <div className='vehicle-add-card' onClick={onClick}>
      <div className='vehicle-add-card__inner'>
        <span className='vehicle-add-card__plus'>+</span>
        <span className='vehicle-add-card__label'>차량 추가</span>
      </div>
    </div>
  )
}
