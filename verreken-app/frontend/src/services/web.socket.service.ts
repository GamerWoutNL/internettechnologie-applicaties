import SockJS from 'sockjs-client'
import Stomp, { Client } from 'webstomp-client'

export default class WebSocketService {
  private socket: unknown
  private stompClient: Client

  connect (onMessageReceive: (message: string) => void): void {
    this.socket = new SockJS('http://localhost:1245/verreken')
    this.stompClient = Stomp.over(this.socket)

    this.stompClient.connect(
      {},
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
      // this.stompClient.send('/app/data', '[{"id":0,"name":"Wout","amount":"54.00","description":"pizza"},{"id":1,"name":"Dirk","amount":"65.00","description":"bier"},{"id":2,"name":"Dennis","amount":"196.00","description":"fiets"}]', {})
    }
  }
}
