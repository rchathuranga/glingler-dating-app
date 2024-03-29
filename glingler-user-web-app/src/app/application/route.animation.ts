import {animate, animation, query, style, transition, trigger} from '@angular/animations';

export const fader = trigger('routeAnimations', [
    transition('* <=> *', [
      query(':enter, :leave', [
        style({
          position: 'absolute',
          left: 0,
          width: '100%',
          opacity: 0,
          transform: 'scale(1) translateY(0%)'
        }),
      ]),
      query(':enter', [
        animate('800ms ease', style({opacity: 1, transform: 'scale(1) translateY(0)'}))
      ])
    ])
  ]);
