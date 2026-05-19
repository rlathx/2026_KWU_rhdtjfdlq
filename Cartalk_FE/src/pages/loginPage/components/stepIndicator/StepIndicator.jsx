import './StepIndicator.css'

export default function StepIndicator({ total, current }) {
  return (
    <div className='step-indicator'>
      {Array.from({ length: total }, (_, i) => {
        const stepNum = i + 1
        const isActive = stepNum <= current
        const isDone = stepNum < current

        return (
          <div
            key={stepNum}
            className={`step-indicator__step ${isActive ? 'step-indicator__step--active' : 'step-indicator__step--idle'}`}
          >
            {isDone ? (
              <img src='/src/assets/step/step-check-icon.svg' alt='완료' width={13} height={13} />
            ) : (
              stepNum
            )}
          </div>
        )
      })}
    </div>
  )
}
