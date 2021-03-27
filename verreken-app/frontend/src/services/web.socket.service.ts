import SockJS from 'sockjs-client'
import Stomp, { Client } from 'webstomp-client'
import { config } from '../config'

export default class WebSocketService {
  private socket: unknown
  private stompClient: Client

  connect (onMessageReceive: (message: string) => void): void {
    this.socket = new SockJS(config.backend.host + '/verreken')
    this.stompClient = Stomp.over(this.socket)

    this.stompClient.connect(
      config.backend.auth,
      (frame) => {
        console.log(frame)

        this.stompClient.subscribe('/topic/data', (tick) => {
          onMessageReceive(tick.body)
        })
      },
      (error) => {
        console.log(error)
      }
    )
  }

  disconnect (): void {
    if (this.stompClient) {
      this.stompClient.disconnect()
    }
  }

  send (payload: string): void {
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send('/app/data', payload, {})
    }
  }
}
