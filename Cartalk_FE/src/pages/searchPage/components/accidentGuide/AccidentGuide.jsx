import { useState } from 'react'
import './AccidentGuide.css'

const GUIDE_DATA = [
  {
    id: 1,
    title: '사고가 나면 즉시 정차하세요.',
    items: [
      '사고 발생 시, 그 자리에 바로 정차하세요.',
      '만약 사고차량을 움직일 수 있다면 신속히 갓 길로 이동하세요.',
    ],
  },
  {
    id: 2,
    title: '사상자의 구호조치를 하세요.',
    items: [
      '사상자가 발생하면 지혈 등의 응급조치를 진행하고 필요에 따라 119를 부르세요.',
      '경미한 부상의 경우 바로 보험회사에 연락하면 보다 빨리 사고를 수습할 수 있습니다.',
    ],
  },
  {
    id: 3,
    title: '사고현장과 관련된 정보를 정리하세요.',
    items: [
      '사고차량의 차량번호, 파손 부위, 바퀴 위치를 사진 찍고, 사고위치를 알 수 있는 배경(길 표지판, 신호등, 차선 등)도 사진으로 찍으세요. 이를 보험회사에 전달하면 나중에 보상을 처리할 때 큰 도움이 됩니다.',
      '사고차량의 네 바퀴 밑과 노면 흔적 등에 스프레이를 뿌려 현장 상황을 표시해 두면 좋아요.',
      '상대 차량번호, 연락처 등을 메모하세요.',
      '목격자를 확보하는 것도 도움이 됩니다.',
    ],
  },
  {
    id: 4,
    title: '보험회사에 연락하세요.',
    items: [
      '사고처리 요령을 잘 알고 있어도 사고가 나면 당황하여 적절하게 대처하기가 어렵습니다. 당황했다고 해서 도의적인 사과를 하는 대신에 일방과실을 인정하거나 손해배상을 약속하면 안됩니다.',
      '기본적인 조치만 취하고 보험회사에 연락해서 도움을 받는 것을 추천합니다.',
    ],
  },
]

export default function AccidentGuide() {
  // 현재 열린 아이템 id — null이면 모두 닫힘, 한 번에 하나만 열림
  const [openId, setOpenId] = useState(null)

  const handleToggle = (id) => {
    setOpenId((prev) => (prev === id ? null : id))
  }

  return (
    <div className='accident-guide'>
      {GUIDE_DATA.map((item) => (
        <div key={item.id} className='accident-guide__item'>
          {/* 헤더 */}
          <button className='accident-guide__header' onClick={() => handleToggle(item.id)}>
            <span className='accident-guide__number'>{String(item.id).padStart(2, '0')}</span>
            <span className='accident-guide__title'>{item.title}</span>
            {/* 화살표 — assets 추가 후 경로 교체 */}
            <img
              className={`accident-guide__arrow${openId === item.id ? ' accident-guide__arrow--open' : ''}`}
              src='/src/assets/guide/arrow.svg'
              alt={openId === item.id ? '닫기' : '열기'}
            />
          </button>

          {/* 펼침 내용 */}
          {openId === item.id && (
            <div className='accident-guide__content'>
              <ul className='accident-guide__list'>
                {item.items.map((text, idx) => (
                  <li key={idx} className='accident-guide__list-item'>
                    <span className='accident-guide__bullet'>•</span>
                    <span>{text}</span>
                  </li>
                ))}
              </ul>
            </div>
          )}
        </div>
      ))}
    </div>
  )
}
