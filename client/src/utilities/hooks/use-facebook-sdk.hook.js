import { EXTERNAL_SCRIPTS } from '@/utilities/constants';
import { useEffect, useState } from 'react';

export const useFacebookSDK = () => {
  const [isFacebookAvailable, setIsFacebookAvailable] = useState(true);

  useEffect(() => {
    // Skip if already loaded
    if (window.FB) return setIsFacebookAvailable(true);

    const script = document.createElement('script');

    script.src = EXTERNAL_SCRIPTS.FACEBOOK_SDK;
    script.async = true;
    script.defer = true;
    script.id = 'facebook-jssdk';

    script.onerror = () => setIsFacebookAvailable(false);
    script.onload = () => setIsFacebookAvailable(true);

    document.head.appendChild(script);

    return () => {
      if (document.head.contains(script)) document.head.removeChild(script);
    };
  }, []);

  return isFacebookAvailable;
};
